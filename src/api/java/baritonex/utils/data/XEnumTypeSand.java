package baritonex.utils.data;

import net.minecraft.block.material.MapColor;

public enum XEnumTypeSand
{
    SAND(0, "sand", "default", MapColor.sandColor),
    RED_SAND(1, "red_sand", "red", MapColor.adobeColor);

    private static final XEnumTypeSand[] META_LOOKUP = new XEnumTypeSand[values().length];
    private final int meta;
    private final String name;
    private final MapColor mapColor;
    private final String unlocalizedName;

    private XEnumTypeSand(int meta, String name, String unlocalizedName, MapColor mapColor)
    {
        this.meta = meta;
        this.name = name;
        this.mapColor = mapColor;
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

    public MapColor getMapColor()
    {
        return this.mapColor;
    }

    public static XEnumTypeSand byMetadata(int meta)
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
        for (XEnumTypeSand blocksand$enumtype : values())
        {
            META_LOOKUP[blocksand$enumtype.getMetadata()] = blocksand$enumtype;
        }
    }
}