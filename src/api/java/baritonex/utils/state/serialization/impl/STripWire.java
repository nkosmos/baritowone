package baritonex.utils.state.serialization.impl;

import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class STripWire extends StateSerializer {

	public STripWire(BlockState blockState) {
		super(blockState);
	}

	@Override
	public int getMetaFromState(IBlockState state)
    {
        int i = 0;

        if (((Boolean)state.getValue(Properties.TRIPWIRE_POWERED)).booleanValue())
        {
            i |= 1;
        }

        if (((Boolean)state.getValue(Properties.TRIPWIRE_SUSPENDED)).booleanValue())
        {
            i |= 2;
        }

        if (((Boolean)state.getValue(Properties.TRIPWIRE_ATTACHED)).booleanValue())
        {
            i |= 4;
        }

        if (((Boolean)state.getValue(Properties.TRIPWIRE_DISARMED)).booleanValue())
        {
            i |= 8;
        }

        return i;
    }
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState()
				.withProperty(Properties.TRIPWIRE_POWERED, Boolean.valueOf((meta & 1) > 0))
				.withProperty(Properties.TRIPWIRE_SUSPENDED, Boolean.valueOf((meta & 2) > 0))
				.withProperty(Properties.TRIPWIRE_ATTACHED, Boolean.valueOf((meta & 4) > 0))
				.withProperty(Properties.TRIPWIRE_DISARMED, Boolean.valueOf((meta & 8) > 0));
	}

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState()
				.withProperty(Properties.TRIPWIRE_POWERED, Boolean.valueOf(false))
				.withProperty(Properties.TRIPWIRE_SUSPENDED, Boolean.valueOf(false))
				.withProperty(Properties.TRIPWIRE_ATTACHED, Boolean.valueOf(false))
				.withProperty(Properties.TRIPWIRE_DISARMED, Boolean.valueOf(false))
				.withProperty(Properties.TRIPWIRE_NORTH, Boolean.valueOf(false))
				.withProperty(Properties.TRIPWIRE_EAST, Boolean.valueOf(false))
				.withProperty(Properties.TRIPWIRE_SOUTH, Boolean.valueOf(false))
				.withProperty(Properties.TRIPWIRE_WEST, Boolean.valueOf(false));
	}

}
