package baritonex.utils.data;

import net.minecraft.block.material.MapColor;

public enum XDirtType
{
    DIRT(0, "dirt", "default", MapColor.dirtColor),
    COARSE_DIRT(1, "coarse_dirt", "coarse", MapColor.dirtColor),
    PODZOL(2, "podzol", MapColor.obsidianColor);

    private static final XDirtType[] METADATA_LOOKUP = new XDirtType[values().length];
    private final int metadata;
    private final String name;
    private final String unlocalizedName;
    private final MapColor field_181067_h;

    private XDirtType(int p_i46396_3_, String p_i46396_4_, MapColor p_i46396_5_)
    {
        this(p_i46396_3_, p_i46396_4_, p_i46396_4_, p_i46396_5_);
    }

    private XDirtType(int p_i46397_3_, String p_i46397_4_, String p_i46397_5_, MapColor p_i46397_6_)
    {
        this.metadata = p_i46397_3_;
        this.name = p_i46397_4_;
        this.unlocalizedName = p_i46397_5_;
        this.field_181067_h = p_i46397_6_;
    }

    public int getMetadata()
    {
        return this.metadata;
    }

    public String getUnlocalizedName()
    {
        return this.unlocalizedName;
    }

    public MapColor func_181066_d()
    {
        return this.field_181067_h;
    }

    public String toString()
    {
        return this.name;
    }

    public static XDirtType byMetadata(int metadata)
    {
        if (metadata < 0 || metadata >= METADATA_LOOKUP.length)
        {
            metadata = 0;
        }

        return METADATA_LOOKUP[metadata];
    }

    public String getName()
    {
        return this.name;
    }

    static {
        for (XDirtType blockdirt$dirttype : values())
        {
            METADATA_LOOKUP[blockdirt$dirttype.getMetadata()] = blockdirt$dirttype;
        }
    }
}