package baritonex.utils.state.serialization.impl;

import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SFire extends StateSerializer {

	public SFire(BlockState blockState) {
		super(blockState);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((Integer)state.getValue(Properties.FIRE_AGE)).intValue();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(Properties.FIRE_AGE, Integer.valueOf(meta));
	}

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState().withProperty(Properties.FIRE_AGE, Integer.valueOf(0)).withProperty(Properties.FIRE_FLIP, Boolean.valueOf(false)).withProperty(Properties.FIRE_ALT, Boolean.valueOf(false)).withProperty(Properties.FIRE_NORTH, Boolean.valueOf(false)).withProperty(Properties.FIRE_EAST, Boolean.valueOf(false)).withProperty(Properties.FIRE_SOUTH, Boolean.valueOf(false)).withProperty(Properties.FIRE_WEST, Boolean.valueOf(false)).withProperty(Properties.FIRE_UPPER, Integer.valueOf(0));
	}

}
