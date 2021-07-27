package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XEnumFacing;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SDispenser extends StateSerializer {

	public SDispenser(BlockState blockState) {
		super(blockState);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
        i = i | ((XEnumFacing)state.getValue(Properties.DISPENSER_FACING)).getIndex();

        if (((Boolean)state.getValue(Properties.DISPENSER_TRIGGERED)).booleanValue())
        {
            i |= 8;
        }

        return i;
	}

	public static XEnumFacing getFacing(int meta)
    {
        return XEnumFacing.getFront(meta & 7);
    }
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(Properties.DISPENSER_FACING, getFacing(meta)).withProperty(Properties.DISPENSER_TRIGGERED, Boolean.valueOf((meta & 8) > 0));
	}

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState().withProperty(Properties.DISPENSER_FACING, XEnumFacing.NORTH).withProperty(Properties.DISPENSER_TRIGGERED, Boolean.valueOf(false));
	}

}
