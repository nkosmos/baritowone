package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XEnumFacing;
import baritonex.utils.data.XEnumHalfStairs;
import baritonex.utils.data.XEnumShapeStairs;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SStairs extends StateSerializer {

	public SStairs(BlockState blockState) {
		super(blockState);
	}

	/**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState iblockstate = this.getDefaultState().withProperty(Properties.STAIRS_HALF, (meta & 4) > 0 ? XEnumHalfStairs.TOP : XEnumHalfStairs.BOTTOM);
        iblockstate = iblockstate.withProperty(Properties.STAIRS_FACING, XEnumFacing.getFront(5 - (meta & 3)));
        return iblockstate;
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;

        if (state.getValue(Properties.STAIRS_HALF) == XEnumHalfStairs.TOP)
        {
            i |= 4;
        }

        i = i | 5 - ((XEnumFacing)state.getValue(Properties.STAIRS_FACING)).getIndex();
        return i;
    }

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState()
				.withProperty(Properties.STAIRS_FACING, XEnumFacing.NORTH)
				.withProperty(Properties.STAIRS_HALF, XEnumHalfStairs.BOTTOM)
				.withProperty(Properties.STAIRS_SHAPE, XEnumShapeStairs.STRAIGHT);
	}

}
