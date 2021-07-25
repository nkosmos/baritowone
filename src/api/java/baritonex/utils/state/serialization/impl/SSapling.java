package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XEnumTypePlanks;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SSapling extends StateSerializer {

	public SSapling(BlockState blockState) {
		super(blockState);
	}

	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(Properties.SAPLING_TYPE, XEnumTypePlanks.byMetadata(meta & 7)).withProperty(Properties.SAPLING_STAGE, Integer.valueOf((meta & 8) >> 3));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | (state.getValue(Properties.SAPLING_TYPE)).getMetadata();
        i = i | ((Integer)state.getValue(Properties.SAPLING_STAGE)).intValue() << 3;
        return i;
    }

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState().withProperty(Properties.SAPLING_TYPE, XEnumTypePlanks.OAK).withProperty(Properties.SAPLING_STAGE, Integer.valueOf(0));
	}

	
}
