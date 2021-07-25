package baritonex.utils.state.serialization.impl;

import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SPressurePlate extends StateSerializer {

	public SPressurePlate(BlockState blockState) {
		super(blockState);
		// TODO Auto-generated constructor stub
	}

	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(Properties.PRESSUREPLATE_POWERED, Boolean.valueOf(false));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
    	return ((Boolean)state.getValue(Properties.PRESSUREPLATE_POWERED)).booleanValue() ? 1 : 0;
    }

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState().withProperty(Properties.PRESSUREPLATE_POWERED, Boolean.valueOf(false));
	}

}
