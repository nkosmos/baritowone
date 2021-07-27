package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XEnumTypeMushroom;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SHugeMushroom extends StateSerializer{

	public SHugeMushroom(BlockState blockState) {
		super(blockState);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((XEnumTypeMushroom)state.getValue(Properties.HUGEMUSHROOM_VARIANT)).getMetadata();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(Properties.HUGEMUSHROOM_VARIANT, XEnumTypeMushroom.byMetadata(meta));
	}

	@Override
	public IBlockState defineDefaultState() {
		// TODO Auto-generated method stub
		return this.blockState.getBaseState().withProperty(Properties.HUGEMUSHROOM_VARIANT, XEnumTypeMushroom.ALL_OUTSIDE);
	}

}
