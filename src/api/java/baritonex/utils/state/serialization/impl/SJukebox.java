package baritonex.utils.state.serialization.impl;

import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SJukebox extends StateSerializer {

	public SJukebox(BlockState blockState) {
		super(blockState);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((Boolean)state.getValue(Properties.JUKEBOX_HAS_RECORD)).booleanValue() ? 1 : 0;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(Properties.JUKEBOX_HAS_RECORD, Boolean.valueOf(meta > 0));
	}

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState().withProperty(Properties.JUKEBOX_HAS_RECORD, Boolean.valueOf(false));
	}

}
