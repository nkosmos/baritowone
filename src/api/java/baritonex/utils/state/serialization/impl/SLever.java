package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XEnumOrientation;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SLever extends StateSerializer {

	public SLever(BlockState blockState) {
		super(blockState);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
        i = i | ((XEnumOrientation)state.getValue(Properties.LEVER_FACING)).getMetadata();

        if (((Boolean)state.getValue(Properties.LEVER_POWERED)).booleanValue())
        {
            i |= 8;
        }

        return i;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(Properties.LEVER_FACING, XEnumOrientation.byMetadata(meta & 7)).withProperty(Properties.LEVER_POWERED, Boolean.valueOf((meta & 8) > 0));
	}

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState().withProperty(Properties.LEVER_FACING, XEnumOrientation.NORTH).withProperty(Properties.LEVER_POWERED, Boolean.valueOf(false));
	}

}
