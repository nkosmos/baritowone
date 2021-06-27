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

package baritone.launch.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import baritone.utils.accessor.IChunkProviderClient;
import net.minecraft.client.multiplayer.ChunkProviderClient;
import net.minecraft.util.LongHashMap;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.chunk.Chunk;

@Mixin(ChunkProviderClient.class)
public class MixinChunkProviderClient implements IChunkProviderClient {

    @Shadow
    private LongHashMap<Chunk> chunkMapping;

    @Override
    public LongHashMap<Chunk> loadedChunks() {
        return this.chunkMapping;
    }

	@Override
	public boolean isChunkGeneratedAt(int x, int z) {
		return this.chunkMapping.containsItem(ChunkCoordIntPair.chunkXZ2Int(x, z));
	}
}
