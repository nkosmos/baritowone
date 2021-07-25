package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XEnumTypeTallGrass;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class STallGrass extends StateSerializer {

	public STallGrass(BlockState blockState) {
		super(blockState);
	}

	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(Properties.TALLGRASS_TYPE, XEnumTypeTallGrass.byMetadata(meta));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(Properties.TALLGRASS_TYPE).getMeta();
    }

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState().withProperty(Properties.TALLGRASS_TYPE, XEnumTypeTallGrass.DEAD_BUSH);
	}

}
