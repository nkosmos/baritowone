package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XEnumFacing;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SFurnace extends StateSerializer {

	public SFurnace(BlockState blockState) {
		super(blockState);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((XEnumFacing)state.getValue(Properties.FURNACE_FACING)).getIndex();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		XEnumFacing enumfacing = XEnumFacing.getFront(meta);

        if (enumfacing.getAxis() == XEnumFacing.Axis.Y)
        {
            enumfacing = XEnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(Properties.FURNACE_FACING, enumfacing);
	}

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState().withProperty(Properties.FURNACE_FACING, XEnumFacing.NORTH);
	}
	
}
