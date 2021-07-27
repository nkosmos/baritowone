package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XEnumTypePlanks;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SPlanks extends StateSerializer {

	public SPlanks(BlockState blockState) {
		super(blockState);
	}

	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(Properties.PLANKS_VARIANT, XEnumTypePlanks.byMetadata(meta));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return (state.getValue(Properties.PLANKS_VARIANT)).getMetadata();
    }
	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState().withProperty(Properties.PLANKS_VARIANT, XEnumTypePlanks.OAK);
	}

}
