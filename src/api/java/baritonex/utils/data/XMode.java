package baritonex.utils.data;

public enum XMode
{
    COMPARE("compare"),
    SUBTRACT("subtract");

    private final String name;

    private XMode(String name)
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