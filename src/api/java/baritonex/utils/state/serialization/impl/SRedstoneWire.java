package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XEnumAttachPosition;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SRedstoneWire extends StateSerializer {

	public SRedstoneWire(BlockState blockState) {
		super(blockState);
	}

	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(Properties.REDSTONEWIRE_POWER, Integer.valueOf(meta));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return ((Integer)state.getValue(Properties.REDSTONEWIRE_POWER)).intValue();
    }

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState()
				.withProperty(Properties.REDSTONEWIRE_NORTH, XEnumAttachPosition.NONE)
				.withProperty(Properties.REDSTONEWIRE_EAST, XEnumAttachPosition.NONE)
				.withProperty(Properties.REDSTONEWIRE_SOUTH, XEnumAttachPosition.NONE)
				.withProperty(Properties.REDSTONEWIRE_WEST, XEnumAttachPosition.NONE)
				.withProperty(Properties.REDSTONEWIRE_POWER, Integer.valueOf(0));
	}

}
