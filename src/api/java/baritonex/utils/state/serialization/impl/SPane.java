package baritonex.utils.state.serialization.impl;

import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SPane extends StateSerializer{

	public SPane(BlockState blockState) {
		super(blockState);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		// TODO Auto-generated method stub
		return this.getDefaultState();
	}

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState()
				.withProperty(Properties.PANE_NORTH, Boolean.valueOf(false))
				.withProperty(Properties.PANE_EAST, Boolean.valueOf(false))
				.withProperty(Properties.PANE_SOUTH, Boolean.valueOf(false))
				.withProperty(Properties.PANE_WEST, Boolean.valueOf(false));
	}

}
