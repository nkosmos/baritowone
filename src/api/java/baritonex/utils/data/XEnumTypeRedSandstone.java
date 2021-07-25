package baritonex.utils.data;

public enum XEnumTypeRedSandstone 
{
    DEFAULT(0, "red_sandstone", "default"),
    CHISELED(1, "chiseled_red_sandstone", "chiseled"),
    SMOOTH(2, "smooth_red_sandstone", "smooth");

    private static final XEnumTypeRedSandstone[] META_LOOKUP = new XEnumTypeRedSandstone[values().length];
    private final int meta;
    private final String name;
    private final String unlocalizedName;

    private XEnumTypeRedSandstone(int meta, String name, String unlocalizedName)
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

    public static XEnumTypeRedSandstone byMetadata(int meta)
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

    static {
        for (XEnumTypeRedSandstone blockredsandstone$enumtype : values())
        {
            META_LOOKUP[blockredsandstone$enumtype.getMetadata()] = blockredsandstone$enumtype;
        }
    }
}