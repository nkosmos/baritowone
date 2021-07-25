package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XEnumTypeStone;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SStone extends StateSerializer {

	public SStone(BlockState blockState) {
		super(blockState);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return (state.getValue(Properties.STONE_VARIANT)).getMetadata();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(Properties.STONE_VARIANT, XEnumTypeStone.byMetadata(meta));
	}

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState().withProperty(Properties.STONE_VARIANT, XEnumTypeStone.STONE);
	}

}
