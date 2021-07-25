/*
 * This file is part of Baritone.
 *
 * Baritone is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Baritone is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Baritone.  If not, see <https://www.gnu.org/licenses/>.
 */

package baritone.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

import baritone.api.cache.ICachedWorld;
import baritone.api.cache.IWorldScanner;
import baritone.api.utils.BetterBlockPos;
import baritone.api.utils.BlockOptionalMetaLookup;
import baritone.api.utils.IPlayerContext;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.XBlockStateSerializer;
import net.minecraft.client.multiplayer.ChunkProviderClient;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.EmptyChunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;

public enum WorldScanner implements IWorldScanner {

    INSTANCE;

    private static final int[] DEFAULT_COORDINATE_ITERATION_ORDER = IntStream.range(0, 16).toArray();

    @Override
    public List<BetterBlockPos> scanChunkRadius(IPlayerContext ctx, BlockOptionalMetaLookup filter, int max, int yLevelThreshold, int maxSearchRadius) {
        ArrayList<BetterBlockPos> res = new ArrayList<>();

        if (filter.blocks().isEmpty()) {
            return res;
        }

        ChunkProviderClient chunkProvider = (ChunkProviderClient) ctx.world().getChunkProvider();

        int maxSearchRadiusSq = maxSearchRadius * maxSearchRadius;
        int playerChunkX = ctx.playerFeet().getX() >> 4;
        int playerChunkZ = ctx.playerFeet().getZ() >> 4;
        int playerY = ctx.playerFeet().getY();

        int playerYBlockStateContainerIndex = playerY >> 4;
        int[] coordinateIterationOrder = IntStream.range(0, 16).boxed().sorted(Comparator.comparingInt(y -> Math.abs(y - playerYBlockStateContainerIndex))).mapToInt(x -> x).toArray();

        int searchRadiusSq = 0;
        boolean foundWithinY = false;
        while (true) {
            boolean allUnloaded = true;
            boolean foundChunks = false;
            for (int xoff = -searchRadiusSq; xoff <= searchRadiusSq; xoff++) {
                for (int zoff = -searchRadiusSq; zoff <= searchRadiusSq; zoff++) {
                    int distance = xoff * xoff + zoff * zoff;
                    if (distance != searchRadiusSq) {
                        continue;
                    }
                    foundChunks = true;
                    int chunkX = xoff + playerChunkX;
                    int chunkZ = zoff + playerChunkZ;
                    Chunk chunk = chunkProvider.provideChunk(chunkX, chunkZ);
                    if (chunk == null || chunk instanceof EmptyChunk) {
                        continue;
                    }
                    allUnloaded = false;
                    if (scanChunkInto(chunkX << 4, chunkZ << 4, chunk, filter, res, max, yLevelThreshold, playerY, coordinateIterationOrder)) {
                        foundWithinY = true;
                    }
                }
            }
            if ((allUnloaded && foundChunks)
                    || (res.size() >= max
                    && (searchRadiusSq > maxSearchRadiusSq || (searchRadiusSq > 1 && foundWithinY)))
            ) {
                return res;
            }
            searchRadiusSq++;
        }
    }

    @Override
    public List<BetterBlockPos> scanChunk(IPlayerContext ctx, BlockOptionalMetaLookup filter, ChunkCoordIntPair pos, int max, int yLevelThreshold) {
        if (filter.blocks().isEmpty()) {
            return Collections.emptyList();
        }

        ChunkProviderClient chunkProvider = (ChunkProviderClient) ctx.world().getChunkProvider();
        Chunk chunk = chunkProvider.provideChunk(pos.chunkXPos, pos.chunkZPos);
        int playerY = ctx.playerFeet().getY();

        if (chunk == null || chunk.isEmpty()) {
            return Collections.emptyList();
        }

        ArrayList<BetterBlockPos> res = new ArrayList<>();
        scanChunkInto(pos.chunkXPos << 4, pos.chunkZPos << 4, chunk, filter, res, max, yLevelThreshold, playerY, DEFAULT_COORDINATE_ITERATION_ORDER);
        return res;
    }

    @Override
    public int repack(IPlayerContext ctx) {
        return this.repack(ctx, 40);
    }

    @Override
    public int repack(IPlayerContext ctx, int range) {
        IChunkProvider chunkProvider = ctx.world().getChunkProvider();
        ICachedWorld cachedWorld = ctx.worldData().getCachedWorld();

        BetterBlockPos playerPos = ctx.playerFeet();

        int playerChunkX = playerPos.getX() >> 4;
        int playerChunkZ = playerPos.getZ() >> 4;

        int minX = playerChunkX - range;
        int minZ = playerChunkZ - range;
        int maxX = playerChunkX + range;
        int maxZ = playerChunkZ + range;

        int queued = 0;
        for (int x = minX; x <= maxX; x++) {
            for (int z = minZ; z <= maxZ; z++) {
                Chunk chunk = chunkProvider.provideChunk(x, z);

                if (chunk != null && !chunk.isEmpty()) {
                    queued++;
                    cachedWorld.queueForPacking(chunk);
                }
            }
        }

        return queued;
    }

    private boolean scanChunkInto(int chunkX, int chunkZ, Chunk chunk, BlockOptionalMetaLookup filter, Collection<BetterBlockPos> result, int max, int yLevelThreshold, int playerY, int[] coordinateIterationOrder) {
        ExtendedBlockStorage[] chunkInternalStorageArray = chunk.getBlockStorageArray();
        boolean foundWithinY = false;
        for (int yIndex = 0; yIndex < 16; yIndex++) {
            int y0 = coordinateIterationOrder[yIndex];
            ExtendedBlockStorage extendedblockstorage = chunkInternalStorageArray[y0];
            if (extendedblockstorage == null) {
                continue;
            }
            int yReal = y0 << 4;
            // the mapping of BlockStateContainer.getIndex from xyz to index is y << 8 | z << 4 | x;
            // for better cache locality, iterate in that order
            for (int y = 0; y < 16; y++) {
                for (int z = 0; z < 16; z++) {
                    for (int x = 0; x < 16; x++) {
                        IBlockState state = XBlockStateSerializer.getStateFromMeta(extendedblockstorage.getBlockByExtId(x, y, z), extendedblockstorage.getExtBlockMetadata(x, y, z));
                        if (filter.has(state.getBlock())) {
                            int yy = yReal | y;
                            if (result.size() >= max) {
                                if (Math.abs(yy - playerY) < yLevelThreshold) {
                                    foundWithinY = true;
                                } else {
                                    if (foundWithinY) {
                                        // have found within Y in this chunk, so don't need to consider outside Y
                                        // TODO continue iteration to one more sorted Y coordinate block
                                        return true;
                                    }
                                }
                            }
                            result.add(new BetterBlockPos(chunkX | x, yy, chunkZ | z));
                        }
                    }
                }
            }
        }
        return foundWithinY;
    }
}
