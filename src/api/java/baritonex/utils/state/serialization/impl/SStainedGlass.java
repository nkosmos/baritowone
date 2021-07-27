package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XEnumDyeColor;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SStainedGlass extends StateSerializer {

	public SStainedGlass(BlockState blockState) {
		super(blockState);
	}

	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(Properties.STAINEDGLASS_COLOR, XEnumDyeColor.byMetadata(meta));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return (state.getValue(Properties.STAINEDGLASS_COLOR)).getMetadata();
    }

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState().withProperty(Properties.STAINEDGLASS_COLOR, XEnumDyeColor.WHITE);
	}

}
