package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XEnumAxis;
import baritonex.utils.data.XEnumTypePlanks;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SOldLog extends StateSerializer {

	public SOldLog(BlockState blockState) {
		super(blockState);
	}

	/**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState iblockstate = this.getDefaultState().withProperty(Properties.OLDLOG_VARIANT, XEnumTypePlanks.byMetadata((meta & 3) % 4));

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

    /**
     * Convert the BlockState into the correct metadata value
     */
    @SuppressWarnings("incomplete-switch")
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | (state.getValue(Properties.OLDLOG_VARIANT)).getMetadata();

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
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState().withProperty(Properties.OLDLOG_VARIANT, XEnumTypePlanks.OAK).withProperty(Properties.LOG_AXIS, XEnumAxis.Y);
	}

}
