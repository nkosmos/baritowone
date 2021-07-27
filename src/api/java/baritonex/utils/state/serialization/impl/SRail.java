package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XEnumRailDirection;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SRail extends StateSerializer {

	public SRail(BlockState blockState) {
		super(blockState);
	}

	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(Properties.RAIL_SHAPE, XEnumRailDirection.byMetadata(meta));
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	public int getMetaFromState(IBlockState state) {
		return (state.getValue(Properties.RAIL_SHAPE)).getMetadata();
	}

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState().withProperty(Properties.RAIL_SHAPE, XEnumRailDirection.NORTH_SOUTH);
	}

}
