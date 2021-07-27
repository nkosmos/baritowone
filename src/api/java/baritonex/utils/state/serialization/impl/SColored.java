package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XEnumDyeColor;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SColored extends StateSerializer {

	public SColored(BlockState blockState) {
		super(blockState);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getMetaFromState(IBlockState state) {
        return ((XEnumDyeColor)state.getValue(Properties.COLORED_COLOR)).getMetadata();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(Properties.COLORED_COLOR, XEnumDyeColor.byMetadata(meta));
	}

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState().withProperty(Properties.COLORED_COLOR, XEnumDyeColor.WHITE);
	}

}