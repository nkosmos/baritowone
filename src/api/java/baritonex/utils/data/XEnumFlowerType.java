package baritonex.utils.data;

import java.util.Collection;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

public enum XEnumFlowerType {
    DANDELION(XEnumFlowerColor.YELLOW, 0, "dandelion"),
    POPPY(XEnumFlowerColor.RED, 0, "poppy"),
    BLUE_ORCHID(XEnumFlowerColor.RED, 1, "blue_orchid", "blueOrchid"),
    ALLIUM(XEnumFlowerColor.RED, 2, "allium"),
    HOUSTONIA(XEnumFlowerColor.RED, 3, "houstonia"),
    RED_TULIP(XEnumFlowerColor.RED, 4, "red_tulip", "tulipRed"),
    ORANGE_TULIP(XEnumFlowerColor.RED, 5, "orange_tulip", "tulipOrange"),
    WHITE_TULIP(XEnumFlowerColor.RED, 6, "white_tulip", "tulipWhite"),
    PINK_TULIP(XEnumFlowerColor.RED, 7, "pink_tulip", "tulipPink"),
    OXEYE_DAISY(XEnumFlowerColor.RED, 8, "oxeye_daisy", "oxeyeDaisy");

    private static final XEnumFlowerType[][] TYPES_FOR_BLOCK = new XEnumFlowerType[XEnumFlowerColor.values().length][];
    private final XEnumFlowerColor blockType;
    private final int meta;
    private final String name;
    private final String unlocalizedName;

    private XEnumFlowerType(XEnumFlowerColor blockType, int meta, String name)
    {
        this(blockType, meta, name, name);
    }

    private XEnumFlowerType(XEnumFlowerColor blockType, int meta, String name, String unlocalizedName)
    {
        this.blockType = blockType;
        this.meta = meta;
        this.name = name;
        this.unlocalizedName = unlocalizedName;
    }

    public XEnumFlowerColor getBlockType()
    {
        return this.blockType;
    }

    public int getMeta()
    {
        return this.meta;
    }

    public static XEnumFlowerType getType(XEnumFlowerColor blockType, int meta)
    {
        XEnumFlowerType[] ablockflower$enumflowertype = TYPES_FOR_BLOCK[blockType.ordinal()];

        if (meta < 0 || meta >= ablockflower$enumflowertype.length)
        {
            meta = 0;
        }

        return ablockflower$enumflowertype[meta];
    }

    public static XEnumFlowerType[] getTypes(XEnumFlowerColor flowerColor)
    {
        return TYPES_FOR_BLOCK[flowerColor.ordinal()];
    }

    public String toString()
    {
        return this.name;
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
        for (final XEnumFlowerColor blockflower$enumflowercolor : XEnumFlowerColor.values())
        {
            Collection<XEnumFlowerType> collection = Collections2.<XEnumFlowerType>filter(Lists.newArrayList(values()), new Predicate<XEnumFlowerType>()
            {
                public boolean apply(XEnumFlowerType p_apply_1_)
                {
                    return p_apply_1_.getBlockType() == blockflower$enumflowercolor;
                }
            });
            TYPES_FOR_BLOCK[blockflower$enumflowercolor.ordinal()] = (XEnumFlowerType[])collection.toArray(new XEnumFlowerType[collection.size()]);
        }
    }
}