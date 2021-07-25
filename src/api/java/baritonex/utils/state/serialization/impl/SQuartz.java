package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XEnumTypeQuartz;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SQuartz extends StateSerializer {

	public SQuartz(BlockState blockState) {
		super(blockState);
	}

	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(Properties.QUARTZ_VARIANT, XEnumTypeQuartz.byMetadata(meta));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return (state.getValue(Properties.QUARTZ_VARIANT)).getMetadata();
    }

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState().withProperty(Properties.QUARTZ_VARIANT, XEnumTypeQuartz.DEFAULT);
	}

}
