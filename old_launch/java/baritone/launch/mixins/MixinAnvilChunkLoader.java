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

import java.io.File;
import java.util.Map;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import baritone.utils.accessor.IAnvilChunkLoader;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.chunk.storage.AnvilChunkLoader;
import net.minecraft.world.chunk.storage.RegionFile;
import net.minecraft.world.chunk.storage.RegionFileCache;

/**
 * @author Brady
 * @since 9/4/2018
 */
@Mixin(AnvilChunkLoader.class)
public class MixinAnvilChunkLoader implements IAnvilChunkLoader {

    @Shadow
    @Final
    private File chunkSaveLocation;
    
    @Shadow
    private Map<ChunkCoordIntPair, NBTTagCompound> chunksToRemove;

    @Override
    public File getChunkSaveLocation() {
        return this.chunkSaveLocation;
    }

	@Override
	public boolean isChunkGeneratedAt(int x, int z) {
		ChunkCoordIntPair chunkpos = new ChunkCoordIntPair(x, z);
        NBTTagCompound nbttagcompound = (NBTTagCompound)this.chunksToRemove.get(chunkpos);
        return nbttagcompound != null ? true : chunkExists(this.chunkSaveLocation, x, z);
	}
	
	@Unique
	private static boolean chunkExists(File file, int x, int z) {
		RegionFile regionfile = getRegionFileIfExists(file, x, z);
        return regionfile != null ? regionfile.isChunkSaved(x & 31, z & 31) : false;
	}
	
	@Unique
	private static RegionFile getRegionFileIfExists(File file, int x, int z) {
		File file1 = new File(file, "region");
        File file2 = new File(file1, "r." + (x >> 5) + "." + (z >> 5) + ".mca");
        RegionFile regionfile = (RegionFile)RegionFileCache.regionsByFilename.get(file2);

        if (regionfile != null) {
            return regionfile;
        } else if (file1.exists() && file2.exists()) {
            if (RegionFileCache.regionsByFilename.size() >= 256) {
            	RegionFileCache.clearRegionFileReferences();
            }

            RegionFile regionfile1 = new RegionFile(file2);
            RegionFileCache.regionsByFilename.put(file2, regionfile1);
            return regionfile1;
        } else {
            return null;
        }
	}
}
