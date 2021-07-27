package baritonex.utils.data;

public enum XEnumTypeSandstone
{
    DEFAULT(0, "sandstone", "default"),
    CHISELED(1, "chiseled_sandstone", "chiseled"),
    SMOOTH(2, "smooth_sandstone", "smooth");

    private static final XEnumTypeSandstone[] META_LOOKUP = new XEnumTypeSandstone[values().length];
    private final int metadata;
    private final String name;
    private final String unlocalizedName;

    private XEnumTypeSandstone(int meta, String name, String unlocalizedName)
    {
        this.metadata = meta;
        this.name = name;
        this.unlocalizedName = unlocalizedName;
    }

    public int getMetadata()
    {
        return this.metadata;
    }

    public String toString()
    {
        return this.name;
    }

    public static XEnumTypeSandstone byMetadata(int meta)
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
        for (XEnumTypeSandstone blocksandstone$enumtype : values())
        {
            META_LOOKUP[blocksandstone$enumtype.getMetadata()] = blocksandstone$enumtype;
        }
    }
}