package baritonex.utils.data;

import net.minecraft.block.material.MapColor;

public enum XEnumTypePlanks
{
    OAK(0, "oak", MapColor.woodColor),
    SPRUCE(1, "spruce", MapColor.obsidianColor),
    BIRCH(2, "birch", MapColor.sandColor),
    JUNGLE(3, "jungle", MapColor.dirtColor),
    ACACIA(4, "acacia", MapColor.adobeColor),
    DARK_OAK(5, "dark_oak", "big_oak", MapColor.brownColor);

    private static final XEnumTypePlanks[] META_LOOKUP = new XEnumTypePlanks[values().length];
    private final int meta;
    private final String name;
    private final String unlocalizedName;
    private final MapColor field_181071_k;

    private XEnumTypePlanks(int p_i46388_3_, String p_i46388_4_, MapColor p_i46388_5_)
    {
        this(p_i46388_3_, p_i46388_4_, p_i46388_4_, p_i46388_5_);
    }

    private XEnumTypePlanks(int p_i46389_3_, String p_i46389_4_, String p_i46389_5_, MapColor p_i46389_6_)
    {
        this.meta = p_i46389_3_;
        this.name = p_i46389_4_;
        this.unlocalizedName = p_i46389_5_;
        this.field_181071_k = p_i46389_6_;
    }

    public int getMetadata()
    {
        return this.meta;
    }

    public MapColor func_181070_c()
    {
        return this.field_181071_k;
    }

    public String toString()
    {
        return this.name;
    }

    public static XEnumTypePlanks byMetadata(int meta)
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
        for (XEnumTypePlanks blockplanks$enumtype : values())
        {
            META_LOOKUP[blockplanks$enumtype.getMetadata()] = blockplanks$enumtype;
        }
    }
}