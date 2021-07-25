package baritonex.utils.data;

public enum XDoorHalf
{
    TOP("top"),
    BOTTOM("bottom");

    private final String name;

    private XDoorHalf(String name)
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