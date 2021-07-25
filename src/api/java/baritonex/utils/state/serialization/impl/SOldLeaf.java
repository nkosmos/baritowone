package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XEnumTypePlanks;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SOldLeaf extends StateSerializer{

	public SOldLeaf(BlockState blockState) {
		super(blockState);
	}

	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState()
        		.withProperty(Properties.OLDLEAF_VARIANT, this.getWoodType(meta))
        		.withProperty(Properties.LEAVES_DECAYABLE, Boolean.valueOf((meta & 4) == 0))
        		.withProperty(Properties.LEAVES_CHECK_DECAY, Boolean.valueOf((meta & 8) > 0));
    }

	public XEnumTypePlanks getWoodType(int meta)
    {
        return XEnumTypePlanks.byMetadata((meta & 3) % 4);
    }
	
    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | (state.getValue(Properties.OLDLEAF_VARIANT)).getMetadata();

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

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState()
				.withProperty(Properties.OLDLEAF_VARIANT, XEnumTypePlanks.OAK)
				.withProperty(Properties.LEAVES_CHECK_DECAY, Boolean.valueOf(true))
				.withProperty(Properties.LEAVES_DECAYABLE, Boolean.valueOf(true));
	}
	

}
