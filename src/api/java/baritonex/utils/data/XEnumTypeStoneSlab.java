package baritonex.utils.data;

import net.minecraft.block.material.MapColor;

public enum XEnumTypeStoneSlab {
	STONE(0, MapColor.stoneColor, "stone"),
    SAND(1, MapColor.sandColor, "sandstone", "sand"),
    WOOD(2, MapColor.woodColor, "wood_old", "wood"),
    COBBLESTONE(3, MapColor.stoneColor, "cobblestone", "cobble"),
    BRICK(4, MapColor.redColor, "brick"),
    SMOOTHBRICK(5, MapColor.stoneColor, "stone_brick", "smoothStoneBrick"),
    NETHERBRICK(6, MapColor.netherrackColor, "nether_brick", "netherBrick"),
    QUARTZ(7, MapColor.quartzColor, "quartz");

    private static final XEnumTypeStoneSlab[] META_LOOKUP = new XEnumTypeStoneSlab[values().length];
    private final int meta;
    private final MapColor field_181075_k;
    private final String name;
    private final String unlocalizedName;

    private XEnumTypeStoneSlab(int p_i46381_3_, MapColor p_i46381_4_, String p_i46381_5_)
    {
        this(p_i46381_3_, p_i46381_4_, p_i46381_5_, p_i46381_5_);
    }

    private XEnumTypeStoneSlab(int p_i46382_3_, MapColor p_i46382_4_, String p_i46382_5_, String p_i46382_6_)
    {
        this.meta = p_i46382_3_;
        this.field_181075_k = p_i46382_4_;
        this.name = p_i46382_5_;
        this.unlocalizedName = p_i46382_6_;
    }

    public int getMetadata()
    {
        return this.meta;
    }

    public MapColor func_181074_c()
    {
        return this.field_181075_k;
    }

    public String toString()
    {
        return this.name;
    }

    public static XEnumTypeStoneSlab byMetadata(int meta)
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
        for(XEnumTypeStoneSlab blockstoneslab$enumtype : values())
        {
            META_LOOKUP[blockstoneslab$enumtype.getMetadata()] = blockstoneslab$enumtype;
        }
    }
}
