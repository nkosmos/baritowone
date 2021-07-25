package baritonex.utils.state.serialization;

import baritonex.utils.state.IBlockState;
import net.minecraft.block.Block;

public interface IStateSerializer<T extends Block> {
	
	int getMetaFromState(IBlockState blockState);
	
	IBlockState getStateFromMeta(int metadata);

}
