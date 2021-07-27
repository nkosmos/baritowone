package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XEnumFacing;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SFenceGate extends StateSerializer {

	public SFenceGate(BlockState blockState) {
		super(blockState);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
        i = i | ((XEnumFacing)state.getValue(Properties.DIRECTIONAL_FACING)).getHorizontalIndex();

        if (((Boolean)state.getValue(Properties.FENCEGATE_POWERED)).booleanValue())
        {
            i |= 8;
        }

        if (((Boolean)state.getValue(Properties.FENCEGATE_OPEN)).booleanValue())
        {
            i |= 4;
        }

        return i;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(Properties.DIRECTIONAL_FACING, XEnumFacing.getHorizontal(meta)).withProperty(Properties.FENCEGATE_OPEN, Boolean.valueOf((meta & 4) != 0)).withProperty(Properties.FENCEGATE_POWERED, Boolean.valueOf((meta & 8) != 0));
	}

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState().withProperty(Properties.FENCEGATE_OPEN, Boolean.valueOf(false)).withProperty(Properties.FENCEGATE_POWERED, Boolean.valueOf(false)).withProperty(Properties.FENCEGATE_IN_WALL, Boolean.valueOf(false));
	}

}
