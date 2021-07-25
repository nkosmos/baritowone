package baritonex.utils.state.serialization;

import java.util.Map;

import com.google.common.collect.Maps;

import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import net.minecraft.block.Block;

public class XBlockStateSerializer {
	
	public static final Map<Block, IBlockState> defaultStates = Maps.newHashMap();
	
	public static IBlockState getStateFromMeta(int metadata) {
		
	}
	
	public static int getMetaFromState(IBlockState state) {
		
	}
	
	public static IBlockState getBlockState(Block block) {
		if(!defaultStates.containsKey(block)) {
			BlockState state = new BlockState(block, Properties.getDefaultProperties(block));
			defaultStates.put(block, state.getBaseState());
		}
		return defaultStates.get(block);
	}
	
}
