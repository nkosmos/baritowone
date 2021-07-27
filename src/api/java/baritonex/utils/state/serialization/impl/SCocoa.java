package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XEnumFacing;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SCocoa extends StateSerializer {

	public SCocoa(BlockState blockState) {
		super(blockState);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
        i = i | ((XEnumFacing)state.getValue(Properties.DIRECTIONAL_FACING)).getHorizontalIndex();
        i = i | ((Integer)state.getValue(Properties.COCOA_AGE)).intValue() << 2;
        return i;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(Properties.DIRECTIONAL_FACING, XEnumFacing.getHorizontal(meta)).withProperty(Properties.COCOA_AGE, Integer.valueOf((meta & 15) >> 2));
	}

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState().withProperty(Properties.DIRECTIONAL_FACING, XEnumFacing.NORTH).withProperty(Properties.COCOA_AGE, Integer.valueOf(0));
	}

}
