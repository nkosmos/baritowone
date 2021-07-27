package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XEnumRailDirection;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SRailPowered extends StateSerializer {

	public SRailPowered(BlockState blockState) {
		super(blockState);
	}

	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(Properties.RAILPOWERED_SHAPE, XEnumRailDirection.byMetadata(meta & 7)).withProperty(Properties.RAILPOWERED_POWERED, Boolean.valueOf((meta & 8) > 0));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | (state.getValue(Properties.RAILPOWERED_SHAPE)).getMetadata();

        if (((Boolean)state.getValue(Properties.RAILPOWERED_POWERED)).booleanValue())
        {
            i |= 8;
        }

        return i;
    }

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState().withProperty(Properties.RAILPOWERED_SHAPE, XEnumRailDirection.NORTH_SOUTH).withProperty(Properties.RAILPOWERED_POWERED, Boolean.valueOf(false));
	}

}
