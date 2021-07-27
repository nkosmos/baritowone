package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XEnumFacing;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SEndPortalFrame extends StateSerializer {

	public SEndPortalFrame(BlockState blockState) {
		super(blockState);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
        i = i | ((XEnumFacing)state.getValue(Properties.ENDPORTALFRAME_FACING)).getHorizontalIndex();

        if (((Boolean)state.getValue(Properties.ENDPORTALFRAME_EYE)).booleanValue())
        {
            i |= 4;
        }

        return i;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(Properties.ENDPORTALFRAME_EYE, Boolean.valueOf((meta & 4) != 0)).withProperty(Properties.ENDPORTALFRAME_FACING, XEnumFacing.getHorizontal(meta & 3));
	}

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState().withProperty(Properties.ENDPORTALFRAME_FACING, XEnumFacing.NORTH).withProperty(Properties.ENDPORTALFRAME_EYE, Boolean.valueOf(false));
	}

}
