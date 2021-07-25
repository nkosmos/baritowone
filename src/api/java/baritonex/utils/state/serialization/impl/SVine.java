package baritonex.utils.state.serialization.impl;

import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SVine extends StateSerializer {

	public SVine(BlockState blockState) {
		super(blockState);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;

        if (((Boolean)state.getValue(Properties.VINE_SOUTH)).booleanValue())
        {
            i |= 1;
        }

        if (((Boolean)state.getValue(Properties.VINE_WEST)).booleanValue())
        {
            i |= 2;
        }

        if (((Boolean)state.getValue(Properties.VINE_NORTH)).booleanValue())
        {
            i |= 4;
        }

        if (((Boolean)state.getValue(Properties.VINE_EAST)).booleanValue())
        {
            i |= 8;
        }

        return i;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState()
				.withProperty(Properties.VINE_SOUTH, Boolean.valueOf((meta & 1) > 0))
				.withProperty(Properties.VINE_WEST, Boolean.valueOf((meta & 2) > 0))
				.withProperty(Properties.VINE_NORTH, Boolean.valueOf((meta & 4) > 0))
				.withProperty(Properties.VINE_EAST, Boolean.valueOf((meta & 8) > 0));
	}

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState()
				.withProperty(Properties.VINE_UP, Boolean.valueOf(false))
				.withProperty(Properties.VINE_NORTH, Boolean.valueOf(false))
				.withProperty(Properties.VINE_EAST, Boolean.valueOf(false))
				.withProperty(Properties.VINE_SOUTH, Boolean.valueOf(false))
				.withProperty(Properties.VINE_WEST, Boolean.valueOf(false));
	}

}
