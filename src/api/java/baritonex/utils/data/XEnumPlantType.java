package baritonex.utils.data;

public enum XEnumPlantType
{
    SUNFLOWER(0, "sunflower"),
    SYRINGA(1, "syringa"),
    GRASS(2, "double_grass", "grass"),
    FERN(3, "double_fern", "fern"),
    ROSE(4, "double_rose", "rose"),
    PAEONIA(5, "paeonia");

    private static final XEnumPlantType[] META_LOOKUP = new XEnumPlantType[values().length];
    private final int meta;
    private final String name;
    private final String unlocalizedName;

    private XEnumPlantType(int meta, String name)
    {
        this(meta, name, name);
    }

    private XEnumPlantType(int meta, String name, String unlocalizedName)
    {
        this.meta = meta;
        this.name = name;
        this.unlocalizedName = unlocalizedName;
    }

    public int getMeta()
    {
        return this.meta;
    }

    public String toString()
    {
        return this.name;
    }

    public static XEnumPlantType byMetadata(int meta)
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
        for (XEnumPlantType blockdoubleplant$enumplanttype : values())
        {
            META_LOOKUP[blockdoubleplant$enumplanttype.getMeta()] = blockdoubleplant$enumplanttype;
        }
    }
}