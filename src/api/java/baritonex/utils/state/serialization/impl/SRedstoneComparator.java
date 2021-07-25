package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XEnumFacing;
import baritonex.utils.data.XMode;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SRedstoneComparator extends StateSerializer {

	public SRedstoneComparator(BlockState blockState) {
		super(blockState);
	}

	public IBlockState getStateFromMeta(int meta)
    {
        return getDefaultState().withProperty(Properties.DIRECTIONAL_FACING, XEnumFacing.getHorizontal(meta)).withProperty(Properties.REDSTONECOMPARATOR_POWERED, Boolean.valueOf((meta & 8) > 0)).withProperty(Properties.REDSTONECOMPARATOR_MODE, (meta & 4) > 0 ? XMode.SUBTRACT : XMode.COMPARE);
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | (state.getValue(Properties.DIRECTIONAL_FACING)).getHorizontalIndex();

        if (((Boolean)state.getValue(Properties.REDSTONECOMPARATOR_POWERED)).booleanValue())
        {
            i |= 8;
        }

        if (state.getValue(Properties.REDSTONECOMPARATOR_MODE) == XMode.SUBTRACT)
        {
            i |= 4;
        }

        return i;
    }

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState().withProperty(Properties.DIRECTIONAL_FACING, XEnumFacing.NORTH).withProperty(Properties.REDSTONECOMPARATOR_POWERED, Boolean.valueOf(false)).withProperty(Properties.REDSTONECOMPARATOR_MODE, XMode.COMPARE);
	}

}
