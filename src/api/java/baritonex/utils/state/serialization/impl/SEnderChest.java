package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XEnumFacing;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SEnderChest extends StateSerializer {

	public SEnderChest(BlockState blockState) {
		super(blockState);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((XEnumFacing)state.getValue(Properties.ENDERCHEST_FACING)).getIndex();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		XEnumFacing enumfacing = XEnumFacing.getFront(meta);

        if (enumfacing.getAxis() == XEnumFacing.Axis.Y)
        {
            enumfacing = XEnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(Properties.ENDERCHEST_FACING, enumfacing);
	}

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState().withProperty(Properties.ENDERCHEST_FACING, XEnumFacing.NORTH);
	}

}
