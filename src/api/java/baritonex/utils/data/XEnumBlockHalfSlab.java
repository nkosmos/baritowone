package baritonex.utils.data;

public enum XEnumBlockHalfSlab
{
    TOP("top"),
    BOTTOM("bottom");

    private final String name;

    private XEnumBlockHalfSlab(String name)
    {
        this.name = name;
    }

    public String toString()
    {
        return this.name;
    }

    public String getName()
    {
        return this.name;
    }
}