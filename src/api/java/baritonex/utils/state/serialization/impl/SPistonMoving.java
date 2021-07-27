package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XEnumFacing;
import baritonex.utils.data.XEnumPistonType;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SPistonMoving extends StateSerializer {

	public SPistonMoving(BlockState blockState) {
		super(blockState);
		// TODO Auto-generated constructor stub
	}

	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(Properties.PISTONMOVING_FACING, getFacing(meta)).withProperty(Properties.PISTONMOVING_TYPE, (meta & 8) > 0 ? XEnumPistonType.STICKY : XEnumPistonType.DEFAULT);
    }

	public static XEnumFacing getFacing(int meta)
    {
        int i = meta & 7;
        return i > 5 ? null : XEnumFacing.getFront(i);
    }
	
    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | (state.getValue(Properties.PISTONMOVING_FACING)).getIndex();

        if (state.getValue(Properties.PISTONMOVING_TYPE) == XEnumPistonType.STICKY)
        {
            i |= 8;
        }

        return i;
    }

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState()
				.withProperty(Properties.PISTONMOVING_FACING, XEnumFacing.NORTH)
				.withProperty(Properties.PISTONMOVING_TYPE, XEnumPistonType.DEFAULT);
	}

}
