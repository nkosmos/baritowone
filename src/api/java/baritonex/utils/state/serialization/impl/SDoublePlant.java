package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XEnumBlockHalfTrapdoor;
import baritonex.utils.data.XEnumFacing;
import baritonex.utils.data.XEnumPlantType;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SDoublePlant extends StateSerializer {

	public SDoublePlant(BlockState blockState) {
		super(blockState);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(Properties.DOUBLEPLANT_HALF) == XEnumBlockHalfTrapdoor.UPPER ? 8 | ((XEnumFacing)state.getValue(Properties.DOUBLEPLANT_FACING)).getHorizontalIndex() : ((XEnumPlantType)state.getValue(Properties.DOUBLEPLANT_VARIANT)).getMeta();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return (meta & 8) > 0 
				? this.getDefaultState().withProperty(Properties.DOUBLEPLANT_HALF, XEnumBlockHalfTrapdoor.UPPER) 
				: this.getDefaultState().withProperty(Properties.DOUBLEPLANT_HALF, XEnumBlockHalfTrapdoor.LOWER).withProperty(Properties.DOUBLEPLANT_VARIANT, XEnumPlantType.byMetadata(meta & 7));
	}

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState()
				.withProperty(Properties.DOUBLEPLANT_VARIANT, XEnumPlantType.SUNFLOWER)
				.withProperty(Properties.DOUBLEPLANT_HALF, XEnumBlockHalfTrapdoor.LOWER)
				.withProperty(Properties.DOUBLEPLANT_FACING, XEnumFacing.NORTH);
	}

}
