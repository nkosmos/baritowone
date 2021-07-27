package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XEnumFlowerTypeForge;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SFlowerPot extends StateSerializer {

	public SFlowerPot(BlockState blockState) {
		super(blockState);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		// TODO Auto-generated method stub
		return ((Integer)state.getValue(Properties.FLOWERPOT_LEGACY_DATA)).intValue();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IBlockState defineDefaultState() {
		// TODO Auto-generated method stub
		return this.blockState.getBaseState().withProperty(Properties.FLOWERPOT_CONTENTS, XEnumFlowerTypeForge.EMPTY).withProperty(Properties.FLOWERPOT_LEGACY_DATA, Integer.valueOf(0));
	}

}
