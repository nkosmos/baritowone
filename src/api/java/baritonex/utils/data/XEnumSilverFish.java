package baritonex.utils.data;

import baritonex.utils.property.Properties;
import baritonex.utils.state.IBlockState;
import baritonex.utils.state.serialization.XBlockStateSerializer;
import net.minecraft.init.Blocks;

public enum XEnumSilverFish {
	STONE(0, "stone")
    {
        public IBlockState getModelBlock()
        {
            return XBlockStateSerializer.getBlockState(Blocks.stone).withProperty(Properties.STONE_VARIANT, XEnumTypeStone.STONE);
        }
    },
    COBBLESTONE(1, "cobblestone", "cobble")
    {
        public IBlockState getModelBlock()
        {
            return XBlockStateSerializer.getBlockState(Blocks.cobblestone);
        }
    },
    STONEBRICK(2, "stone_brick", "brick")
    {
        public IBlockState getModelBlock()
        { 
            return XBlockStateSerializer.getBlockState(Blocks.stonebrick).withProperty(Properties.STONEBRICK_VARIANT, XEnumTypeStonebrick.DEFAULT);
        }
    },
    MOSSY_STONEBRICK(3, "mossy_brick", "mossybrick")
    {
        public IBlockState getModelBlock()
        {
            return XBlockStateSerializer.getBlockState(Blocks.stonebrick).withProperty(Properties.STONEBRICK_VARIANT, XEnumTypeStonebrick.MOSSY);
        }
    },
    CRACKED_STONEBRICK(4, "cracked_brick", "crackedbrick")
    {
        public IBlockState getModelBlock()
        {
            return XBlockStateSerializer.getBlockState(Blocks.stonebrick).withProperty(Properties.STONEBRICK_VARIANT, XEnumTypeStonebrick.CRACKED);
        }
    },
    CHISELED_STONEBRICK(5, "chiseled_brick", "chiseledbrick")
    {
        public IBlockState getModelBlock()
        {
            return XBlockStateSerializer.getBlockState(Blocks.stonebrick).withProperty(Properties.STONEBRICK_VARIANT, XEnumTypeStonebrick.CHISELED);
        }
    };

    private static final XEnumSilverFish[] META_LOOKUP = new XEnumSilverFish[values().length];
    private final int meta;
    private final String name;
    private final String unlocalizedName;

    private XEnumSilverFish(int meta, String name)
    {
        this(meta, name, name);
    }

    private XEnumSilverFish(int meta, String name, String unlocalizedName)
    {
        this.meta = meta;
        this.name = name;
        this.unlocalizedName = unlocalizedName;
    }

    public int getMetadata()
    {
        return this.meta;
    }

    public String toString()
    {
        return this.name;
    }

    public static XEnumSilverFish byMetadata(int meta)
    {
        if (meta < 0 || meta >= META_LOOKUP.length)
        {
            meta = 0;
        }

        return META_LOOKUP[meta];
    }

    public String getName()
    {
        return this.name;
    }

    public String getUnlocalizedName()
    {
        return this.unlocalizedName;
    }

    public abstract IBlockState getModelBlock();

    public static XEnumSilverFish forModelBlock(IBlockState model)
    {
        for (XEnumSilverFish blocksilverfish$enumtype : values())
        {
            if (model == blocksilverfish$enumtype.getModelBlock())
            {
                return blocksilverfish$enumtype;
            }
        }

        return STONE;
    }

    static {
        for (XEnumSilverFish blocksilverfish$enumtype : values())
        {
            META_LOOKUP[blocksilverfish$enumtype.getMetadata()] = blocksilverfish$enumtype;
        }
    }
}
