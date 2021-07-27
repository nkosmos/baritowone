package baritonex.utils.data;

public class XTuple<A, B>
{
    private A a;
    private B b;

    public XTuple(A aIn, B bIn)
    {
        this.a = aIn;
        this.b = bIn;
    }

    /**
     * Get the first Object in the Tuple
     */
    public A getFirst()
    {
        return this.a;
    }

    /**
     * Get the second Object in the Tuple
     */
    public B getSecond()
    {
        return this.b;
    }
}