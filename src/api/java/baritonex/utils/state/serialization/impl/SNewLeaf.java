package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XEnumTypePlanks;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SNewLeaf extends StateSerializer {

	public SNewLeaf(BlockState blockState) {
		super(blockState);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
        i = i | (state.getValue(Properties.NEWLEAF_VARIANT)).getMetadata() - 4;

        if (!((Boolean)state.getValue(Properties.LEAVES_DECAYABLE)).booleanValue())
        {
            i |= 4;
        }

        if (((Boolean)state.getValue(Properties.LEAVES_CHECK_DECAY)).booleanValue())
        {
            i |= 8;
        }

        return i;
	}

	public XEnumTypePlanks getWoodType(int meta)
    {
        return XEnumTypePlanks.byMetadata((meta & 3) + 4);
    }

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(Properties.NEWLEAF_VARIANT, this.getWoodType(meta)).withProperty(Properties.LEAVES_DECAYABLE, Boolean.valueOf((meta & 4) == 0)).withProperty(Properties.LEAVES_CHECK_DECAY, Boolean.valueOf((meta & 8) > 0));
	}

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState().withProperty(Properties.NEWLEAF_VARIANT, XEnumTypePlanks.ACACIA).withProperty(Properties.LEAVES_CHECK_DECAY, Boolean.valueOf(true)).withProperty(Properties.LEAVES_DECAYABLE, Boolean.valueOf(true));
	}

}
