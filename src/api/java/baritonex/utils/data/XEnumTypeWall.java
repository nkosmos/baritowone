package baritonex.utils.data;

public enum XEnumTypeWall {
	NORMAL(0, "cobblestone", "normal"),
    MOSSY(1, "mossy_cobblestone", "mossy");

    private static final XEnumTypeWall[] META_LOOKUP = new XEnumTypeWall[values().length];
    private final int meta;
    private final String name;
    private String unlocalizedName;
    
    private XEnumTypeWall(int meta, String name, String unlocalizedName)
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

    public static XEnumTypeWall byMetadata(int meta)
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
        for (XEnumTypeWall blockwall$enumtype : values())
        {
            META_LOOKUP[blockwall$enumtype.getMetadata()] = blockwall$enumtype;
        }
    }
}
