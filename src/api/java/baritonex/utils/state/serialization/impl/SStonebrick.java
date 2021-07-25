package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XEnumTypeStonebrick;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SStonebrick extends StateSerializer {

	public SStonebrick(BlockState blockState) {
		super(blockState);
	}

	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(Properties.STONEBRICK_VARIANT, XEnumTypeStonebrick.byMetadata(meta));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return (state.getValue(Properties.STONEBRICK_VARIANT)).getMetadata();
    }

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState().withProperty(Properties.STONEBRICK_VARIANT, XEnumTypeStonebrick.DEFAULT);
	}

}
