package baritonex.utils.data;

public enum XTrapDoorHalf
{
    TOP("top"),
    BOTTOM("bottom");

    private final String name;

    private XTrapDoorHalf(String name)
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