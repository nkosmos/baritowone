package baritonex.utils.state.serialization;

import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;

public abstract class StateSerializer {
	
	private final IBlockState defaultState;
	protected final BlockState blockState;
	
	public StateSerializer(BlockState blockState) {
		this.blockState = blockState;
		this.defaultState = defineDefaultState();
	}
	
	public abstract int getMetaFromState(IBlockState state);
	
	public abstract IBlockState getStateFromMeta(int meta);
	
	public abstract IBlockState defineDefaultState();
	
	public IBlockState getDefaultState() {
		return defaultState;
	}

}
