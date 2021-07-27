package baritonex.utils.data;

public enum XEnumTypeStonebrick {
	DEFAULT(0, "stonebrick", "default"),
    MOSSY(1, "mossy_stonebrick", "mossy"),
    CRACKED(2, "cracked_stonebrick", "cracked"),
    CHISELED(3, "chiseled_stonebrick", "chiseled");

    private static final XEnumTypeStonebrick[] META_LOOKUP = new XEnumTypeStonebrick[values().length];
    private final int meta;
    private final String name;
    private final String unlocalizedName;

    private XEnumTypeStonebrick(int meta, String name, String unlocalizedName)
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

    public static XEnumTypeStonebrick byMetadata(int meta)
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
        for (XEnumTypeStonebrick blockstonebrick$enumtype : values())
        {
            META_LOOKUP[blockstonebrick$enumtype.getMetadata()] = blockstonebrick$enumtype;
        }
    }
}
