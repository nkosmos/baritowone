package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XEnumFlowerColor;
import baritonex.utils.data.XEnumFlowerType;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SFlower extends StateSerializer {

	public SFlower(BlockState blockState) {
		super(blockState);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((XEnumFlowerType)state.getValue(Properties.FLOWER_TYPE)).getMeta();
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(Properties.FLOWER_TYPE, XEnumFlowerType.getType(XEnumFlowerColor.RED, meta));
	}

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState().withProperty(Properties.FLOWER_TYPE, XEnumFlowerType.POPPY);
	}

}
