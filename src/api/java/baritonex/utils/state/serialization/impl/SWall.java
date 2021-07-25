package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XEnumTypeWall;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SWall extends StateSerializer {

	public SWall(BlockState blockState) {
		super(blockState);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(Properties.WALL_VARIANT).getMetadata();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(Properties.WALL_VARIANT, XEnumTypeWall.byMetadata(meta));
	}

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState()
				.withProperty(Properties.WALL_UP, Boolean.valueOf(false))
				.withProperty(Properties.WALL_NORTH, Boolean.valueOf(false))
				.withProperty(Properties.WALL_EAST, Boolean.valueOf(false))
				.withProperty(Properties.WALL_SOUTH, Boolean.valueOf(false))
				.withProperty(Properties.WALL_WEST, Boolean.valueOf(false))
				.withProperty(Properties.WALL_VARIANT, XEnumTypeWall.NORMAL);
	}
	

}
