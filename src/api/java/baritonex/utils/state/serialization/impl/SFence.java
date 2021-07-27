package baritonex.utils.state.serialization.impl;

import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SFence extends StateSerializer {

	public SFence(BlockState blockState) {
		super(blockState);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState();
	}

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState()
				.withProperty(Properties.FENCE_NORTH, Boolean.valueOf(false))
				.withProperty(Properties.FENCE_EAST, Boolean.valueOf(false))
				.withProperty(Properties.FENCE_SOUTH, Boolean.valueOf(false))
				.withProperty(Properties.FENCE_WEST, Boolean.valueOf(false));
	}

}
