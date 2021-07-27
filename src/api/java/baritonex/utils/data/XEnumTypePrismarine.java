package baritonex.utils.data;

public enum XEnumTypePrismarine
{
    ROUGH(0, "prismarine", "rough"),
    BRICKS(1, "prismarine_bricks", "bricks"),
    DARK(2, "dark_prismarine", "dark");

    private static final XEnumTypePrismarine[] META_LOOKUP = new XEnumTypePrismarine[values().length];
    private final int meta;
    private final String name;
    private final String unlocalizedName;

    private XEnumTypePrismarine(int meta, String name, String unlocalizedName)
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

    public static XEnumTypePrismarine byMetadata(int meta)
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
        for (XEnumTypePrismarine blockprismarine$enumtype : values())
        {
            META_LOOKUP[blockprismarine$enumtype.getMetadata()] = blockprismarine$enumtype;
        }
    }
}