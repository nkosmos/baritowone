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

package baritone.utils;

import baritone.Baritone;
import baritone.api.utils.IPlayerContext;
import baritone.cache.CachedRegion;
import baritone.cache.WorldData;
import baritone.utils.accessor.IChunkProviderClient;
import baritonex.utils.math.BlockPos;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.XBlockStateSerializer;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.util.LongHashMap;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

/**
 * Wraps get for chuck caching capability
 *
 * @author leijurv
 */
public class BlockStateInterface {

    private final LongHashMap loadedChunks;
    private final WorldData worldData;
    protected final IBlockAccess world;
    public final BlockPos.MutableBlockPos isPassableBlockPos;
    public final IBlockAccess access;

    private Chunk prev = null;
    private CachedRegion prevCached = null;

    private final boolean useTheRealWorld;

    private static final IBlockState AIR = XBlockStateSerializer.getBlockState(Blocks.air);

    public BlockStateInterface(IPlayerContext ctx) {
        this(ctx, false);
    }

    public BlockStateInterface(IPlayerContext ctx, boolean copyLoadedChunks) {
        this(ctx.world(), (WorldData) ctx.worldData(), copyLoadedChunks);
    }

    public BlockStateInterface(World world, WorldData worldData, boolean copyLoadedChunks) {
        this.world = world;
        this.worldData = worldData;
        LongHashMap worldLoaded = ((IChunkProviderClient) world.getChunkProvider()).loadedChunks();
        if (copyLoadedChunks) {
            this.loadedChunks = clone(worldLoaded); // make a copy that we can safely access from another thread
        } else {
            this.loadedChunks = worldLoaded; // this will only be used on the main thread
        }
        this.useTheRealWorld = !Baritone.settings().pathThroughCachedOnly.value;
        if (!Minecraft.getMinecraft().isCallingFromMinecraftThread()) {
            throw new IllegalStateException();
        }
        this.isPassableBlockPos = new BlockPos.MutableBlockPos();
        this.access = new BlockStateInterfaceAccessWrapper(this);
    }
    
    private LongHashMap clone(LongHashMap original){
    	LongHashMap coolMap = new LongHashMap();
    	coolMap.capacity = original.capacity;
    	coolMap.hashArray = original.hashArray;
    	coolMap.modCount = original.modCount;
    	coolMap.numHashElements = original.numHashElements;
    	coolMap.percentUseable = original.percentUseable; 
    	return coolMap;
    }

    public boolean worldContainsLoadedChunk(int blockX, int blockZ) {
        return loadedChunks.containsItem(ChunkCoordIntPair.chunkXZ2Int(blockX >> 4, blockZ >> 4));
    }

    public static Block getBlock(IPlayerContext ctx, BlockPos pos) { // won't be called from the pathing thread because the pathing thread doesn't make a single blockpos pog
        return get(ctx, pos).getBlock();
    }

    public static IBlockState get(IPlayerContext ctx, BlockPos pos) {
        return new BlockStateInterface(ctx).get0(pos.getX(), pos.getY(), pos.getZ()); // immense iq
        // can't just do world().get because that doesn't work for out of bounds
        // and toBreak and stuff fails when the movement is instantiated out of load range but it's not able to BlockStateInterface.get what it's going to walk on
    }

    public IBlockState get0(BlockPos pos) {
        return get0(pos.getX(), pos.getY(), pos.getZ());
    }

    public IBlockState get0(int x, int y, int z) { // Mickey resigned

        // Invalid vertical position
        if (y < 0 || y >= 256) {
            return AIR;
        }

        if (useTheRealWorld) {
            Chunk cached = prev;
            // there's great cache locality in block state lookups
            // generally it's within each movement
            // if it's the same chunk as last time
            // we can just skip the mc.world.getChunk lookup
            // which is a Long2ObjectOpenHashMap.get
            // see issue #113
            if (cached != null && cached.xPosition == x >> 4 && cached.zPosition == z >> 4) {
                return XBlockStateSerializer.getStateFromChunk(cached, new BlockPos(x, y, z));
            }
            Chunk chunk = (Chunk) loadedChunks.getValueByKey(ChunkCoordIntPair.chunkXZ2Int(x >> 4, z >> 4));

            if (chunk != null && chunk.isChunkLoaded) {
                prev = chunk;
                return XBlockStateSerializer.getStateFromChunk(chunk, new BlockPos(x, y, z));
            }
        }
        // same idea here, skip the Long2ObjectOpenHashMap.get if at all possible
        // except here, it's 512x512 tiles instead of 16x16, so even better repetition
        CachedRegion cached = prevCached;
        if (cached == null || cached.getX() != x >> 9 || cached.getZ() != z >> 9) {
            if (worldData == null) {
                return AIR;
            }
            CachedRegion region = worldData.cache.getRegion(x >> 9, z >> 9);
            if (region == null) {
                return AIR;
            }
            prevCached = region;
            cached = region;
        }
        IBlockState type = cached.getBlock(x & 511, y, z & 511);
        if (type == null) {
            return AIR;
        }
        return type;
    }

    public boolean isLoaded(int x, int z) {
        Chunk prevChunk = prev;
        if (prevChunk != null && prevChunk.xPosition == x >> 4 && prevChunk.zPosition == z >> 4) {
            return true;
        }
        prevChunk = (Chunk) loadedChunks.getValueByKey(ChunkCoordIntPair.chunkXZ2Int(x >> 4, z >> 4));
        if (prevChunk != null && prevChunk.isChunkLoaded) {
            prev = prevChunk;
            return true;
        }
        CachedRegion prevRegion = prevCached;
        if (prevRegion != null && prevRegion.getX() == x >> 9 && prevRegion.getZ() == z >> 9) {
            return prevRegion.isCached(x & 511, z & 511);
        }
        if (worldData == null) {
            return false;
        }
        prevRegion = worldData.cache.getRegion(x >> 9, z >> 9);
        if (prevRegion == null) {
            return false;
        }
        prevCached = prevRegion;
        return prevRegion.isCached(x & 511, z & 511);
    }
}
