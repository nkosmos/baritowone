package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XEnumFacing;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SRedstoneRepeater extends StateSerializer {

	public SRedstoneRepeater(BlockState blockState) {
		super(blockState);
	}

	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(Properties.DIRECTIONAL_FACING, XEnumFacing.getHorizontal(meta)).withProperty(Properties.REDSTONEREPEATER_LOCKED, Boolean.valueOf(false)).withProperty(Properties.REDSTONEREPEATER_DELAY, Integer.valueOf(1 + (meta >> 2)));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | (state.getValue(Properties.DIRECTIONAL_FACING)).getHorizontalIndex();
        i = i | ((Integer)state.getValue(Properties.REDSTONEREPEATER_DELAY)).intValue() - 1 << 2;
        return i;
    }
    
	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState().withProperty(Properties.DIRECTIONAL_FACING, XEnumFacing.NORTH).withProperty(Properties.REDSTONEREPEATER_DELAY, Integer.valueOf(1)).withProperty(Properties.REDSTONEREPEATER_LOCKED, Boolean.valueOf(false));
	}

}
