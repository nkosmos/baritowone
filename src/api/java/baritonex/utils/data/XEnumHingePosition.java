package baritonex.utils.data;

public enum XEnumHingePosition	
{
    LEFT,
    RIGHT;

    public String toString()
    {
        return this.getName();
    }

    public String getName()
    {
        return this == LEFT ? "left" : "right";
    }
}