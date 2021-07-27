package baritonex.utils.data;

public enum XEnumTypeTallGrass {
	DEAD_BUSH(0, "dead_bush"),
    GRASS(1, "tall_grass"),
    FERN(2, "fern");

    private static final XEnumTypeTallGrass[] META_LOOKUP = new XEnumTypeTallGrass[values().length];
    private final int meta;
    private final String name;

    private XEnumTypeTallGrass(int meta, String name)
    {
        this.meta = meta;
        this.name = name;
    }

    public int getMeta()
    {
        return this.meta;
    }

    public String toString()
    {
        return this.name;
    }

    public static XEnumTypeTallGrass byMetadata(int meta)
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

    static {
        for (XEnumTypeTallGrass blocktallgrass$enumtype : values())
        {
            META_LOOKUP[blocktallgrass$enumtype.getMeta()] = blocktallgrass$enumtype;
        }
    }
}
