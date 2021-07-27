package baritonex.utils.state.serialization.impl;

import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SNetherWart extends StateSerializer {

	public SNetherWart(BlockState blockState) {
		super(blockState);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((Integer)state.getValue(Properties.NETHERWART_AGE)).intValue();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(Properties.NETHERWART_AGE, Integer.valueOf(meta));
	}

	@Override
	public IBlockState defineDefaultState() {
		// TODO Auto-generated method stub
		return this.blockState.getBaseState().withProperty(Properties.NETHERWART_AGE, Integer.valueOf(0));
	}

}
