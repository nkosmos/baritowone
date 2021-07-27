package baritonex.utils.data;

public enum XEnumTypeMushroom
{
    NORTH_WEST(1, "north_west"),
    NORTH(2, "north"),
    NORTH_EAST(3, "north_east"),
    WEST(4, "west"),
    CENTER(5, "center"),
    EAST(6, "east"),
    SOUTH_WEST(7, "south_west"),
    SOUTH(8, "south"),
    SOUTH_EAST(9, "south_east"),
    STEM(10, "stem"),
    ALL_INSIDE(0, "all_inside"),
    ALL_OUTSIDE(14, "all_outside"),
    ALL_STEM(15, "all_stem");

    private static final XEnumTypeMushroom[] META_LOOKUP = new XEnumTypeMushroom[16];
    private final int meta;
    private final String name;

    private XEnumTypeMushroom(int meta, String name)
    {
        this.meta = meta;
        this.name = name;
    }

    public int getMetadata()
    {
        return this.meta;
    }

    public String toString()
    {
        return this.name;
    }

    public static XEnumTypeMushroom byMetadata(int meta)
    {
        if (meta < 0 || meta >= META_LOOKUP.length)
        {
            meta = 0;
        }

        XEnumTypeMushroom blockhugemushroom$enumtype = META_LOOKUP[meta];
        return blockhugemushroom$enumtype == null ? META_LOOKUP[0] : blockhugemushroom$enumtype;
    }

    public String getName()
    {
        return this.name;
    }

    static {
        for (XEnumTypeMushroom blockhugemushroom$enumtype : values())
        {
            META_LOOKUP[blockhugemushroom$enumtype.getMetadata()] = blockhugemushroom$enumtype;
        }
    }
}