package baritonex.utils.data;

public enum XEnumPistonType
{
    DEFAULT("normal"),
    STICKY("sticky");

    private final String VARIANT;

    private XEnumPistonType(String name)
    {
        this.VARIANT = name;
    }

    public String toString()
    {
        return this.VARIANT;
    }

    public String getName()
    {
        return this.VARIANT;
    }
}