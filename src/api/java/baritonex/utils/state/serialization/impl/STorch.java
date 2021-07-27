package baritonex.utils.state.serialization.impl;

import baritonex.utils.data.XEnumFacing;
import baritonex.utils.property.Properties;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class STorch extends StateSerializer {

	public STorch(BlockState blockState) {
		super(blockState);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;

        switch ((XEnumFacing)state.getValue(Properties.TORCH_FACING))
        {
            case EAST:
                i = i | 1;
                break;
            case WEST:
                i = i | 2;
                break;
            case SOUTH:
                i = i | 3;
                break;
            case NORTH:
                i = i | 4;
                break;
            case DOWN:
            case UP:
            default:
                i = i | 5;
        }

        return i;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState iblockstate = this.getDefaultState();

        switch (meta)
        {
            case 1:
                iblockstate = iblockstate.withProperty(Properties.TORCH_FACING, XEnumFacing.EAST);
                break;
            case 2:
                iblockstate = iblockstate.withProperty(Properties.TORCH_FACING, XEnumFacing.WEST);
                break;
            case 3:
                iblockstate = iblockstate.withProperty(Properties.TORCH_FACING, XEnumFacing.SOUTH);
                break;
            case 4:
                iblockstate = iblockstate.withProperty(Properties.TORCH_FACING, XEnumFacing.NORTH);
                break;
            case 5:
            default:
                iblockstate = iblockstate.withProperty(Properties.TORCH_FACING, XEnumFacing.UP);
        }

        return iblockstate;
	}

	@Override
	public IBlockState defineDefaultState() {
		return this.blockState.getBaseState().withProperty(Properties.TORCH_FACING, XEnumFacing.UP);
	}

}
