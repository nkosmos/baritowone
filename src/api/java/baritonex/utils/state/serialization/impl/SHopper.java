package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XEnumFacing;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SHopper extends StateSerializer {

	public SHopper(BlockState blockState) {
		super(blockState);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
        i = i | ((XEnumFacing)state.getValue(Properties.HOPPER_FACING)).getIndex();

        if (!((Boolean)state.getValue(Properties.HOPPER_ENABLED)).booleanValue())
        {
            i |= 8;
        }

        return i;
	}
	
	public static XEnumFacing getFacing(int meta)
    {
        return XEnumFacing.getFront(meta & 7);
    }
	
	public static boolean isEnabled(int meta)
    {
        return (meta & 8) != 8;
    }
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(Properties.HOPPER_FACING, getFacing(meta)).withProperty(Properties.HOPPER_ENABLED, Boolean.valueOf(isEnabled(meta)));
	}

	@Override
	public IBlockState defineDefaultState() {
		// TODO Auto-generated method stub
		return this.blockState.getBaseState().withProperty(Properties.HOPPER_FACING, XEnumFacing.DOWN).withProperty(Properties.HOPPER_ENABLED, Boolean.valueOf(true));
	}

}
