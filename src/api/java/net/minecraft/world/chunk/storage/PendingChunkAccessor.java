package net.minecraft.world.chunk.storage;

import java.util.List;

import baritone.utils.accessor.IAnvilChunkLoader;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.ChunkCoordIntPair;

public class PendingChunkAccessor {
	
	private final IAnvilChunkLoader anvilChunkLoader;
	
	public PendingChunkAccessor(IAnvilChunkLoader anvilChunkLoader) {
		this.anvilChunkLoader = anvilChunkLoader;
	}
	
	public NBTTagCompound getTagForChunk(ChunkCoordIntPair coords) {
		NBTTagCompound nbttagcompound = null;
        for(AnvilChunkLoader.PendingChunk c : (List<AnvilChunkLoader.PendingChunk>)anvilChunkLoader.getChunksToRemove()) {
        	if(c.chunkCoordinate.equals(coords)) {
        		nbttagcompound = c.nbtTags;
        		break;
        	}
        }
        return nbttagcompound;
	}

}
