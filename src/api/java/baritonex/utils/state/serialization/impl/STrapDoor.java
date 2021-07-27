package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XEnumFacing;
import baritonex.utils.data.XTrapDoorHalf;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class STrapDoor extends StateSerializer {

	public STrapDoor(BlockState blockState) {
		super(blockState);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
        i = i | getMetaForFacing((XEnumFacing)state.getValue(Properties.TRAPDOOR_FACING));

        if (((Boolean)state.getValue(Properties.TRAPDOOR_OPEN)).booleanValue())
        {
            i |= 4;
        }

        if (state.getValue(Properties.TRAPDOOR_HALF) == XTrapDoorHalf.TOP)
        {
            i |= 8;
        }

        return i;
	}

	protected static XEnumFacing getFacing(int meta)
    {
        switch (meta & 3)
        {
            case 0:
                return XEnumFacing.NORTH;
            case 1:
                return XEnumFacing.SOUTH;
            case 2:
                return XEnumFacing.WEST;
            case 3:
            default:
                return XEnumFacing.EAST;
        }
    }

    protected static int getMetaForFacing(XEnumFacing facing)
    {
        switch (facing)
        {
            case NORTH:
                return 0;
            case SOUTH:
                return 1;
            case WEST:
                return 2;
            case EAST:
            default:
                return 3;
        }
    }
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState()
				.withProperty(Properties.TRAPDOOR_FACING, getFacing(meta))
				.withProperty(Properties.TRAPDOOR_OPEN, Boolean.valueOf((meta & 4) != 0))
				.withProperty(Properties.TRAPDOOR_HALF, (meta & 8) == 0 ? XTrapDoorHalf.BOTTOM : XTrapDoorHalf.TOP);
	}

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState()
				.withProperty(Properties.TRAPDOOR_FACING, XEnumFacing.NORTH)
				.withProperty(Properties.TRAPDOOR_OPEN, Boolean.valueOf(false))
				.withProperty(Properties.TRAPDOOR_HALF, XTrapDoorHalf.BOTTOM);
	}

}
