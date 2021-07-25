package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XEnumFacing;
import baritonex.utils.data.XEnumPartType;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SBed extends StateSerializer {

	public SBed(BlockState blockState) {
		super(blockState);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
        i = i | ((XEnumFacing)state.getValue(Properties.DIRECTIONAL_FACING)).getHorizontalIndex();

        if (state.getValue(Properties.BED_PART) == XEnumPartType.HEAD)
        {
            i |= 8;

            if (((Boolean)state.getValue(Properties.BED_OCCUPIED)).booleanValue())
            {
                i |= 4;
            }
        }

        return i;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		XEnumFacing enumfacing = XEnumFacing.getHorizontal(meta);
        return (meta & 8) > 0 ? this.getDefaultState().withProperty(Properties.BED_PART, XEnumPartType.HEAD).withProperty(Properties.DIRECTIONAL_FACING, enumfacing).withProperty(Properties.BED_OCCUPIED, Boolean.valueOf((meta & 4) > 0)) : this.getDefaultState().withProperty(Properties.BED_PART, XEnumPartType.FOOT).withProperty(Properties.DIRECTIONAL_FACING, enumfacing);
	}

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState().withProperty(Properties.BED_PART, XEnumPartType.FOOT).withProperty(Properties.BED_OCCUPIED, Boolean.valueOf(false));
	}

}
