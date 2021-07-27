package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XEnumFacing;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class STripWireHook extends StateSerializer {

	public STripWireHook(BlockState blockState) {
		super(blockState);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
        i = i | ((XEnumFacing)state.getValue(Properties.TRIPWIREHOOK_FACING)).getHorizontalIndex();

        if (((Boolean)state.getValue(Properties.TRIPWIREHOOK_POWERED)).booleanValue())
        {
            i |= 8;
        }

        if (((Boolean)state.getValue(Properties.TRIPWIREHOOK_ATTACHED)).booleanValue())
        {
            i |= 4;
        }

        return i;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState()
				.withProperty(Properties.TRIPWIREHOOK_FACING, XEnumFacing.getHorizontal(meta & 3))
				.withProperty(Properties.TRIPWIREHOOK_POWERED, Boolean.valueOf((meta & 8) > 0))
				.withProperty(Properties.TRIPWIREHOOK_ATTACHED, Boolean.valueOf((meta & 4) > 0));
	}

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState()
				.withProperty(Properties.TRIPWIREHOOK_FACING, XEnumFacing.NORTH)
				.withProperty(Properties.TRIPWIREHOOK_POWERED, Boolean.valueOf(false))
				.withProperty(Properties.TRIPWIREHOOK_ATTACHED, Boolean.valueOf(false))
				.withProperty(Properties.TRIPWIREHOOK_SUSPENDED, Boolean.valueOf(false));
	}

}
