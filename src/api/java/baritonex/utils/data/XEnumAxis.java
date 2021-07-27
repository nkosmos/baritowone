package baritonex.utils.data;

public enum XEnumAxis
{
    X("x"),
    Y("y"),
    Z("z"),
    NONE("none");

    private final String name;

    private XEnumAxis(String name)
    {
        this.name = name;
    }

    public String toString()
    {
        return this.name;
    }

    public static XEnumAxis fromFacingAxis(XEnumFacing.Axis axis)
    {
        switch (axis)
        {
            case X:
                return X;

            case Y:
                return Y;

            case Z:
                return Z;

            default:
                return NONE;
        }
    }

    public String getName()
    {
        return this.name;
    }
}