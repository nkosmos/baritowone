package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XEnumFacing;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SAnvil extends StateSerializer {

	public SAnvil(BlockState blockState) { super(blockState); }

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
        i = i | ((XEnumFacing)state.getValue(Properties.ANVIL_FACING)).getHorizontalIndex();
        i = i | ((Integer)state.getValue(Properties.ANVIL_DAMAGE)).intValue() << 2;
        return i;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(Properties.ANVIL_FACING, XEnumFacing.getHorizontal(meta & 3)).withProperty(Properties.ANVIL_DAMAGE, Integer.valueOf((meta & 15) >> 2));
	}

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState().withProperty(Properties.ANVIL_FACING, XEnumFacing.NORTH).withProperty(Properties.ANVIL_DAMAGE, Integer.valueOf(0));
	}

}
