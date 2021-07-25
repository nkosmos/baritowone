package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XEnumFacing;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SPistonBase extends StateSerializer {

	public SPistonBase(BlockState blockState) {
		super(blockState);
	}

	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(Properties.PISTONBASE_FACING, SPistonMoving.getFacing(meta)).withProperty(Properties.PISTONBASE_EXTENDED, Boolean.valueOf((meta & 8) > 0));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | (state.getValue(Properties.PISTONBASE_FACING)).getIndex();

        if (((Boolean)state.getValue(Properties.PISTONBASE_EXTENDED)).booleanValue())
        {
            i |= 8;
        }

        return i;
    }

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState().withProperty(Properties.PISTONBASE_FACING, XEnumFacing.NORTH).withProperty(Properties.PISTONBASE_EXTENDED, Boolean.valueOf(false));
	}

}
