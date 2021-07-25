package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XEnumFacing;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SButton extends StateSerializer {

	public SButton(BlockState blockState) {
		super(blockState);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i;

        switch ((XEnumFacing)state.getValue(Properties.BUTTON_FACING))
        {
            case EAST:
                i = 1;
                break;

            case WEST:
                i = 2;
                break;

            case SOUTH:
                i = 3;
                break;

            case NORTH:
                i = 4;
                break;

            case UP:
            default:
                i = 5;
                break;

            case DOWN:
                i = 0;
        }

        if (((Boolean)state.getValue(Properties.BUTTON_POWERED)).booleanValue())
        {
            i |= 8;
        }

        return i;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		XEnumFacing enumfacing;

        switch (meta & 7)
        {
            case 0:
                enumfacing = XEnumFacing.DOWN;
                break;

            case 1:
                enumfacing = XEnumFacing.EAST;
                break;

            case 2:
                enumfacing = XEnumFacing.WEST;
                break;

            case 3:
                enumfacing = XEnumFacing.SOUTH;
                break;

            case 4:
                enumfacing = XEnumFacing.NORTH;
                break;

            case 5:
            default:
                enumfacing = XEnumFacing.UP;
        }

        return this.getDefaultState().withProperty(Properties.BUTTON_FACING, enumfacing).withProperty(Properties.BUTTON_POWERED, Boolean.valueOf((meta & 8) > 0));
	}

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState().withProperty(Properties.BUTTON_FACING, XEnumFacing.NORTH).withProperty(Properties.BUTTON_POWERED, Boolean.valueOf(false));
	}

}
