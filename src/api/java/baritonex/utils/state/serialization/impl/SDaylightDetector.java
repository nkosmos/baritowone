package baritonex.utils.state.serialization.impl;

import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SDaylightDetector extends StateSerializer{

	public SDaylightDetector(BlockState blockState) {
		super(blockState);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((Integer)state.getValue(Properties.DAYLIGHTDETECTOR_POWER)).intValue();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(Properties.DAYLIGHTDETECTOR_POWER, Integer.valueOf(meta));
	}

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState().withProperty(Properties.DAYLIGHTDETECTOR_POWER, Integer.valueOf(0));
	}

}
