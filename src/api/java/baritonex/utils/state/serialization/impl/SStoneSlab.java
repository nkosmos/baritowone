package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XEnumBlockHalfSlab;
import baritonex.utils.data.XEnumTypeStoneSlab;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SStoneSlab extends StateSerializer {

	public SStoneSlab(BlockState blockState) {
		super(blockState);
	}

	/**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState iblockstate = this.getDefaultState().withProperty(Properties.STONESLAB_VARIANT, XEnumTypeStoneSlab.byMetadata(meta & 7));

        if (iblockstate.getBlock().isFullBlock())
        {
            iblockstate = iblockstate.withProperty(Properties.STONESLAB_SEAMLESS, Boolean.valueOf((meta & 8) != 0));
        }
        else
        {
            iblockstate = iblockstate.withProperty(Properties.SLAB_HALF, (meta & 8) == 0 ? XEnumBlockHalfSlab.BOTTOM : XEnumBlockHalfSlab.TOP);
        }

        return iblockstate;
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | state.getValue(Properties.STONESLAB_VARIANT).getMetadata();

        if (state.getBlock().isFullBlock())
        {
            if (((Boolean)state.getValue(Properties.STONESLAB_SEAMLESS)).booleanValue())
            {
                i |= 8;
            }
        }
        else if (state.getValue(Properties.SLAB_HALF) == XEnumBlockHalfSlab.TOP)
        {
            i |= 8;
        }

        return i;
    }

	@Override
	public IBlockState defineDefaultState() {
		IBlockState iblockstate = this.blockState.getBaseState();

        if (iblockstate.getBlock().isFullBlock())
        {
            iblockstate = iblockstate.withProperty(Properties.STONESLAB_SEAMLESS, Boolean.valueOf(false));
        }
        else
        {
            iblockstate = iblockstate.withProperty(Properties.SLAB_HALF, XEnumBlockHalfSlab.BOTTOM);
        }

        return iblockstate.withProperty(Properties.STONESLAB_VARIANT, XEnumTypeStoneSlab.STONE);
	}

}
