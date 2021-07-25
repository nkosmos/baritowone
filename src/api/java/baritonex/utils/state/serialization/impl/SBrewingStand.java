package baritonex.utils.state.serialization.impl;

import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SBrewingStand extends StateSerializer {

	public SBrewingStand(BlockState blockState) {
		super(blockState);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;

        for (int j = 0; j < 3; ++j)
        {
            if (((Boolean)state.getValue(Properties.BREWINGSTAND_HAS_BOTTLE[j])).booleanValue())
            {
                i |= 1 << j;
            }
        }

        return i;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState iblockstate = this.getDefaultState();

        for (int i = 0; i < 3; ++i)
        {
            iblockstate = iblockstate.withProperty(Properties.BREWINGSTAND_HAS_BOTTLE[i], Boolean.valueOf((meta & 1 << i) > 0));
        }

        return iblockstate;
	}

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState().withProperty(Properties.BREWINGSTAND_HAS_BOTTLE[0], Boolean.valueOf(false)).withProperty(Properties.BREWINGSTAND_HAS_BOTTLE[1], Boolean.valueOf(false)).withProperty(Properties.BREWINGSTAND_HAS_BOTTLE[2], Boolean.valueOf(false));
	}

}
