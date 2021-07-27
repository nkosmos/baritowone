package baritonex.utils.data;

public enum XEnumShapeStairs {
	 STRAIGHT("straight"),
     INNER_LEFT("inner_left"),
     INNER_RIGHT("inner_right"),
     OUTER_LEFT("outer_left"),
     OUTER_RIGHT("outer_right");

     private final String name;

     private XEnumShapeStairs(String name)
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
