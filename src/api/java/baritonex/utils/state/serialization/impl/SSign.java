package baritonex.utils.state.serialization.impl;

import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SSign extends StateSerializer {

	public SSign(BlockState blockState) {
		super(blockState);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return null;
	}

	@Override
	public IBlockState defineDefaultState() { //TODO: fuck
		return null;
	}

}
