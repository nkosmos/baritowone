package baritonex.utils.state.serialization.impl;

import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SPressurePlateWeighted extends StateSerializer {

	public SPressurePlateWeighted(BlockState blockState) {
		super(blockState);
		// TODO Auto-generated constructor stub
	}

	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(Properties.PRESSUREPLATEWEIGHTED_POWER, Integer.valueOf(meta));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return ((Integer)state.getValue(Properties.PRESSUREPLATEWEIGHTED_POWER)).intValue();
    }

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState().withProperty(Properties.PRESSUREPLATEWEIGHTED_POWER, Integer.valueOf(0));
	}

}
