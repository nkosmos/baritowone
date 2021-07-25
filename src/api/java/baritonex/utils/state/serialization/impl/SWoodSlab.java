package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XEnumBlockHalfSlab;
import baritonex.utils.data.XEnumTypePlanks;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SWoodSlab extends StateSerializer {

	public SWoodSlab(BlockState blockState) {
		super(blockState);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
        i = i | ((XEnumTypePlanks)state.getValue(Properties.WOODSLAB_VARIANT)).getMetadata();

        if (!state.getBlock().isFullBlock() && state.getValue(Properties.SLAB_HALF) == XEnumBlockHalfSlab.TOP)
        {
            i |= 8;
        }

        return i;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState iblockstate = this.getDefaultState().withProperty(Properties.WOODSLAB_VARIANT, XEnumTypePlanks.byMetadata(meta & 7));

        if (!iblockstate.getBlock().isFullBlock())
        {
            iblockstate = iblockstate.withProperty(Properties.SLAB_HALF, (meta & 8) == 0 ? XEnumBlockHalfSlab.BOTTOM : XEnumBlockHalfSlab.TOP);
        }

        return iblockstate;
	}

	@Override
	public IBlockState defineDefaultState() {
		IBlockState iblockstate = this.blockState.getBaseState();

        if (!iblockstate.getBlock().isFullBlock())
        {
            iblockstate = iblockstate.withProperty(Properties.SLAB_HALF, XEnumBlockHalfSlab.BOTTOM);
        }

        return iblockstate.withProperty(Properties.WOODSLAB_VARIANT, XEnumTypePlanks.OAK);
	}

}
