package baritonex.utils.data;

public enum XEnumHalfStairs {
	TOP("top"),
    BOTTOM("bottom");

    private final String name;

    private XEnumHalfStairs(String name)
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
