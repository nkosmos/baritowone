package baritonex.utils.state.serialization;

import java.util.Map;

import com.google.common.collect.Maps;

import baritone.api.utils.BetterBlockPos;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.chunk.Chunk;

public class XBlockStateSerializer {
	
	
	public static final Map<Block, IBlockState> defaultStates = Maps.newHashMap();
	public static final Map<Block, BlockState> idkStates = Maps.newHashMap();
	
	public static IBlockState getStateFromMeta(Block block, int metadata) {
		
	}
	
	public static int getMetaFromState(IBlockState state) {
		
	}
	
	public static IBlockState getStateFromWorld(IBlockAccess access, int x, int y, int z) {
		return getStateFromMeta(access.getBlock(x, y, z), access.getBlockMetadata(x, y, z));
	}
	
	public static IBlockState getStateFromWorld(IBlockAccess access, BetterBlockPos bp) {
		return getStateFromWorld(access, bp.x, bp.y, bp.z);
	}
	
	public static IBlockState getStateFromChunk(Chunk access, int x, int y, int z) {
		return getStateFromMeta(access.getBlock(x, y, z), access.getBlockMetadata(x, y, z));
	}
	
	public static IBlockState getStateFromChunk(Chunk access, BetterBlockPos bp) {
		return getStateFromChunk(access, bp.x, bp.y, bp.z);
	}
	
	public static IBlockState getBlockState(Block block) {
		if(!defaultStates.containsKey(block)) {
			BlockState state = getBlockState2(block);
			idkStates.put(block, state);
			defaultStates.put(block, state.getBaseState());
		}
		return defaultStates.get(block);
	}
	
	public static BlockState getBlockState2(Block block) {
		if(!idkStates.containsKey(block)) {
			BlockState state = new BlockState(block, Properties.getDefaultProperties(block));
			idkStates.put(block, state);
			return state;
		}
		return idkStates.get(block);
	}
	
}
