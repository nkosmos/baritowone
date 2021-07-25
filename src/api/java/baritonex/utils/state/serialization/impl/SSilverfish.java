package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XEnumSilverFish;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SSilverfish extends StateSerializer {

	public SSilverfish(BlockState blockState) {
		super(blockState);
	}

	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(Properties.SILVERFISH_VARIANT, XEnumSilverFish.byMetadata(meta));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return (state.getValue(Properties.SILVERFISH_VARIANT)).getMetadata();
    }

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState().withProperty(Properties.SILVERFISH_VARIANT, XEnumSilverFish.STONE);
	}

}
