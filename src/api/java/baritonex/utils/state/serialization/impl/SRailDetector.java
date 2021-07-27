package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XEnumRailDirection;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SRailDetector extends StateSerializer {

	public SRailDetector(BlockState blockState) {
		super(blockState);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
        i = i | (state.getValue(Properties.RAILDETECTOR_SHAPE)).getMetadata();

        if (((Boolean)state.getValue(Properties.RAILDETECTOR_POWERED)).booleanValue())
        {
            i |= 8;
        }

        return i;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(Properties.RAILDETECTOR_SHAPE, XEnumRailDirection.byMetadata(meta & 7)).withProperty(Properties.RAILDETECTOR_POWERED, Boolean.valueOf((meta & 8) > 0));
	}

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState().withProperty(Properties.RAILDETECTOR_POWERED, Boolean.valueOf(false)).withProperty(Properties.RAILDETECTOR_SHAPE, XEnumRailDirection.NORTH_SOUTH);
	}
	
	

}
