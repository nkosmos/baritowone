package baritonex.utils.data;

public enum XEnumPartType 
{
    HEAD("head"),
    FOOT("foot");

    private final String name;

    private XEnumPartType(String name)
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