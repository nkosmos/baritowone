package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XEnumFacing;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SPumpkin extends StateSerializer {

	public SPumpkin(BlockState blockState) {
		super(blockState);
	}

	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(Properties.DIRECTIONAL_FACING, XEnumFacing.getHorizontal(meta));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return (state.getValue(Properties.DIRECTIONAL_FACING)).getHorizontalIndex();
    }

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState().withProperty(Properties.DIRECTIONAL_FACING, XEnumFacing.NORTH);
	}

}
