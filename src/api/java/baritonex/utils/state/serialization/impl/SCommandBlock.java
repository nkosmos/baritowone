package baritonex.utils.state.serialization.impl;

import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SCommandBlock extends StateSerializer {

	public SCommandBlock(BlockState blockState) {
		super(blockState);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;

        if (((Boolean)state.getValue(Properties.COMMANDBLOCK_TRIGGERED)).booleanValue())
        {
            i |= 1;
        }

        return i;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(Properties.COMMANDBLOCK_TRIGGERED, Boolean.valueOf((meta & 1) > 0));
	}

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState().withProperty(Properties.COMMANDBLOCK_TRIGGERED, Boolean.valueOf(false));
	}

}
