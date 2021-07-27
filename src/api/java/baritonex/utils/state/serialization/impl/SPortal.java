package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XEnumFacing;
import baritonex.utils.data.XEnumFacing.Axis;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SPortal extends StateSerializer {

	public SPortal(BlockState blockState) {
		super(blockState);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		Axis axis = state.getValue(Properties.PORTAL_AXIS);
		return axis == Axis.X ? 1 : (axis == Axis.Z ? 2 : 0);
	}

	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(Properties.PORTAL_AXIS, (meta & 3) == 2 ? XEnumFacing.Axis.Z : XEnumFacing.Axis.X);
    }

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState().withProperty(Properties.PORTAL_AXIS, XEnumFacing.Axis.X);
	}

}
