package baritonex.utils.data;

public enum XEnumBlockHalfTrapdoor 
{
    UPPER,
    LOWER;

    public String toString()
    {
        return this.getName();
    }

    public String getName()
    {
        return this == UPPER ? "upper" : "lower";
    }
}