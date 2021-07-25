package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XEnumDyeColor;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SStainedGlassPane extends StateSerializer {

	public SStainedGlassPane(BlockState blockState) {
		super(blockState);
	}

	/**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(Properties.STAINEDGLASSPANE_COLOR, XEnumDyeColor.byMetadata(meta));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return (state.getValue(Properties.STAINEDGLASSPANE_COLOR)).getMetadata();
    }

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState()
				.withProperty(Properties.PANE_NORTH, Boolean.valueOf(false))
				.withProperty(Properties.PANE_EAST, Boolean.valueOf(false))
				.withProperty(Properties.PANE_SOUTH, Boolean.valueOf(false))
				.withProperty(Properties.PANE_WEST, Boolean.valueOf(false))
				.withProperty(Properties.STAINEDGLASSPANE_COLOR, XEnumDyeColor.WHITE);
	}

}
