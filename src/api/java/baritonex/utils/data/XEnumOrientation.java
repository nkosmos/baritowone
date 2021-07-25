package baritonex.utils.data;

import net.minecraft.util.EnumFacing;

public enum XEnumOrientation
{
    DOWN_X(0, "down_x", XEnumFacing.DOWN),
    EAST(1, "east", XEnumFacing.EAST),
    WEST(2, "west", XEnumFacing.WEST),
    SOUTH(3, "south", XEnumFacing.SOUTH),
    NORTH(4, "north", XEnumFacing.NORTH),
    UP_Z(5, "up_z", XEnumFacing.UP),
    UP_X(6, "up_x", XEnumFacing.UP),
    DOWN_Z(7, "down_z", XEnumFacing.DOWN);

    private static final XEnumOrientation[] META_LOOKUP = new XEnumOrientation[values().length];
    private final int meta;
    private final String name;
    private final XEnumFacing facing;

    private XEnumOrientation(int meta, String name, XEnumFacing facing)
    {
        this.meta = meta;
        this.name = name;
        this.facing = facing;
    }

    public int getMetadata()
    {
        return this.meta;
    }

    public XEnumFacing getFacing()
    {
        return this.facing;
    }

    public String toString()
    {
        return this.name;
    }

    public static XEnumOrientation byMetadata(int meta)
    {
        if (meta < 0 || meta >= META_LOOKUP.length)
        {
            meta = 0;
        }

        return META_LOOKUP[meta];
    }

    public static XEnumOrientation forFacings(EnumFacing clickedSide, XEnumFacing entityFacing)
    {
        switch (clickedSide)
        {
            case DOWN:
                switch (entityFacing.getAxis())
                {
                    case X:
                        return DOWN_X;

                    case Z:
                        return DOWN_Z;

                    default:
                        throw new IllegalArgumentException("Invalid entityFacing " + entityFacing + " for facing " + clickedSide);
                }

            case UP:
                switch (entityFacing.getAxis())
                {
                    case X:
                        return UP_X;

                    case Z:
                        return UP_Z;

                    default:
                        throw new IllegalArgumentException("Invalid entityFacing " + entityFacing + " for facing " + clickedSide);
                }

            case NORTH:
                return NORTH;

            case SOUTH:
                return SOUTH;

            case WEST:
                return WEST;

            case EAST:
                return EAST;

            default:
                throw new IllegalArgumentException("Invalid facing: " + clickedSide);
        }
    }

    public String getName()
    {
        return this.name;
    }

    static {
        for (XEnumOrientation blocklever$enumorientation : values())
        {
            META_LOOKUP[blocklever$enumorientation.getMetadata()] = blocklever$enumorientation;
        }
    }
}