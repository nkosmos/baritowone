package baritonex.utils.data;

public enum XEnumTypeQuartz 
{
    DEFAULT(0, "default", "default"),
    CHISELED(1, "chiseled", "chiseled"),
    LINES_Y(2, "lines_y", "lines"),
    LINES_X(3, "lines_x", "lines"),
    LINES_Z(4, "lines_z", "lines");

    private static final XEnumTypeQuartz[] META_LOOKUP = new XEnumTypeQuartz[values().length];
    private final int meta;
    private final String field_176805_h;
    private final String unlocalizedName;

    private XEnumTypeQuartz(int meta, String name, String unlocalizedName)
    {
        this.meta = meta;
        this.field_176805_h = name;
        this.unlocalizedName = unlocalizedName;
    }

    public int getMetadata()
    {
        return this.meta;
    }

    public String toString()
    {
        return this.unlocalizedName;
    }

    public static XEnumTypeQuartz byMetadata(int meta)
    {
        if (meta < 0 || meta >= META_LOOKUP.length)
        {
            meta = 0;
        }

        return META_LOOKUP[meta];
    }

    public String getName()
    {
        return this.field_176805_h;
    }

    static {
        for (XEnumTypeQuartz blockquartz$enumtype : values())
        {
            META_LOOKUP[blockquartz$enumtype.getMetadata()] = blockquartz$enumtype;
        }
    }
}