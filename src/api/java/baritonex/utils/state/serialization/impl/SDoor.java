package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XEnumDoorHalf;
import baritonex.utils.data.XEnumFacing;
import baritonex.utils.data.XEnumHingePosition;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SDoor extends StateSerializer {

	public SDoor(BlockState blockState) {
		super(blockState);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;

        if (state.getValue(Properties.DOOR_HALF) == XEnumDoorHalf.UPPER)
        {
            i = i | 8;

            if (state.getValue(Properties.DOOR_HINGE) == XEnumHingePosition.RIGHT)
            {
                i |= 1;
            }

            if (((Boolean)state.getValue(Properties.DOOR_POWERED)).booleanValue())
            {
                i |= 2;
            }
        }
        else
        {
            i = i | ((XEnumFacing)state.getValue(Properties.DOOR_FACING)).rotateY().getHorizontalIndex();

            if (((Boolean)state.getValue(Properties.DOOR_OPEN)).booleanValue())
            {
                i |= 4;
            }
        }

        return i;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return (meta & 8) > 0 ? this.getDefaultState().withProperty(Properties.DOOR_HALF, XEnumDoorHalf.UPPER).withProperty(Properties.DOOR_HINGE, (meta & 1) > 0 ? XEnumHingePosition.RIGHT : XEnumHingePosition.LEFT).withProperty(Properties.DOOR_POWERED, Boolean.valueOf((meta & 2) > 0)) : this.getDefaultState().withProperty(Properties.DOOR_HALF, XEnumDoorHalf.LOWER).withProperty(Properties.DOOR_FACING, XEnumFacing.getHorizontal(meta & 3).rotateYCCW()).withProperty(Properties.DOOR_OPEN, Boolean.valueOf((meta & 4) > 0));
	}

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState().withProperty(Properties.DOOR_FACING, XEnumFacing.NORTH).withProperty(Properties.DOOR_OPEN, Boolean.valueOf(false)).withProperty(Properties.DOOR_HINGE, XEnumHingePosition.LEFT).withProperty(Properties.DOOR_POWERED, Boolean.valueOf(false)).withProperty(Properties.DOOR_HALF, XEnumDoorHalf.LOWER);
	}

}
