package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XEnumAxis;
import baritonex.utils.data.XEnumTypePlanks;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SNewLog extends StateSerializer{

	public SNewLog(BlockState blockState) {
		super(blockState);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("incomplete-switch")
	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
        i = i | state.getValue(Properties.NEWLOG_VARIANT).getMetadata() - 4;

        switch (state.getValue(Properties.LOG_AXIS))
        {
            case X:
                i |= 4;
                break;
            case Z:
                i |= 8;
                break;
            case NONE:
                i |= 12;
        }

        return i;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState iblockstate = this.getDefaultState().withProperty(Properties.NEWLOG_VARIANT, XEnumTypePlanks.byMetadata((meta & 3) + 4));

        switch (meta & 12)
        {
            case 0:
                iblockstate = iblockstate.withProperty(Properties.LOG_AXIS, XEnumAxis.Y);
                break;
            case 4:
                iblockstate = iblockstate.withProperty(Properties.LOG_AXIS, XEnumAxis.X);
                break;
            case 8:
                iblockstate = iblockstate.withProperty(Properties.LOG_AXIS, XEnumAxis.Z);
                break;
            default:
                iblockstate = iblockstate.withProperty(Properties.LOG_AXIS, XEnumAxis.NONE);
        }

        return iblockstate;
	}

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState().withProperty(Properties.NEWLOG_VARIANT, XEnumTypePlanks.ACACIA).withProperty(Properties.LOG_AXIS, XEnumAxis.Y);
	}

}
