package baritonex.utils.data;

public enum XEnumAttachPosition
{
    UP("up"),
    SIDE("side"),
    NONE("none");

    private final String name;

    private XEnumAttachPosition(String name)
    {
        this.name = name;
    }

    public String toString()
    {
        return this.getName();
    }

    public String getName()
    {
        return this.name;
    }
}