package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XEnumFacing;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SSkull extends StateSerializer {

	public SSkull(BlockState blockState) {
		super(blockState);
	}

	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(Properties.SKULL_FACING, XEnumFacing.getFront(meta & 7)).withProperty(Properties.SKULL_NODROP, Boolean.valueOf((meta & 8) > 0));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | (state.getValue(Properties.SKULL_FACING)).getIndex();

        if (((Boolean)state.getValue(Properties.SKULL_NODROP)).booleanValue())
        {
            i |= 8;
        }

        return i;
    }

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState().withProperty(Properties.SKULL_FACING, XEnumFacing.NORTH).withProperty(Properties.SKULL_NODROP, Boolean.valueOf(false));
	}

}
