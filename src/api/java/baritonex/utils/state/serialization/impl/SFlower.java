package baritonex.utils.state.serialization.impl;

import com.google.common.base.Predicate;

import baritonex.utils.data.XEnumFlowerColor;
import baritonex.utils.data.XEnumFlowerType;
import baritonex.utils.property.IProperty;
import baritonex.utils.property.impl.PropertyEnum;
import baritonex.utils.state.BlockState;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.StateSerializer;

public class SFlower extends StateSerializer {

	public SFlower(BlockState blockState) {
		super(blockState);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((XEnumFlowerType)state.getValue(this.getTypeProperty())).getMeta();
	}

	public IProperty<XEnumFlowerType> getTypeProperty()
    {
        return PropertyEnum.<XEnumFlowerType>create("type", XEnumFlowerType.class, new Predicate<XEnumFlowerType>()
        {
            public boolean apply(XEnumFlowerType p_apply_1_)
            {
                return true;
            }
        });
    }
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(this.getTypeProperty(), XEnumFlowerType.getType(XEnumFlowerColor.RED, meta));
	}

	@Override
	public IBlockState defineDefaultState() {
		// TODO Auto-generated method stub
		return this.blockState.getBaseState().withProperty(this.getTypeProperty(), XEnumFlowerType.POPPY);
	}

}
