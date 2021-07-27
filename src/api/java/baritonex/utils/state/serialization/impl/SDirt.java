package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XDirtType;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SDirt extends StateSerializer{

	public SDirt(BlockState blockState) {
		super(blockState);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return (state.getValue(Properties.DIRT_VARIANT)).getMetadata();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(Properties.DIRT_VARIANT, XDirtType.byMetadata(meta));
	}

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState().withProperty(Properties.DIRT_VARIANT, XDirtType.DIRT).withProperty(Properties.DIRT_SNOWY, Boolean.valueOf(false));
	}
	

}
