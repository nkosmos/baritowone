package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XEnumTypeSandstone;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SSandstone extends StateSerializer {

	public SSandstone(BlockState blockState) {
		super(blockState);
		// TODO Auto-generated constructor stub
	}

	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(Properties.SANDSTONE_TYPE, XEnumTypeSandstone.byMetadata(meta));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return (state.getValue(Properties.SANDSTONE_TYPE)).getMetadata();
    }

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState().withProperty(Properties.SANDSTONE_TYPE, XEnumTypeSandstone.DEFAULT);
	}

}
