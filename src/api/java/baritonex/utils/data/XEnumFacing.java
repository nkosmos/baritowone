package baritonex.utils.data;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;

import baritonex.utils.XVec3i;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;

public enum XEnumFacing implements IVanillaReimplementation<EnumFacing>
{
    DOWN("DOWN", 0, 0, 1, -1, "down", XEnumFacing.AxisDirection.NEGATIVE, XEnumFacing.Axis.Y, new XVec3i(0, -1, 0), EnumFacing.DOWN),
    UP("UP", 1, 1, 0, -1, "up", XEnumFacing.AxisDirection.POSITIVE, XEnumFacing.Axis.Y, new XVec3i(0, 1, 0), EnumFacing.UP),
    NORTH("NORTH", 2, 2, 3, 2, "north", XEnumFacing.AxisDirection.NEGATIVE, XEnumFacing.Axis.Z, new XVec3i(0, 0, -1), EnumFacing.NORTH),
    SOUTH("SOUTH", 3, 3, 2, 0, "south", XEnumFacing.AxisDirection.POSITIVE, XEnumFacing.Axis.Z, new XVec3i(0, 0, 1), EnumFacing.SOUTH),
    WEST("WEST", 4, 4, 5, 1, "west", XEnumFacing.AxisDirection.NEGATIVE, XEnumFacing.Axis.X, new XVec3i(-1, 0, 0), EnumFacing.WEST),
    EAST("EAST", 5, 5, 4, 3, "east", XEnumFacing.AxisDirection.POSITIVE, XEnumFacing.Axis.X, new XVec3i(1, 0, 0), EnumFacing.EAST);

    /** Ordering index for D-U-N-S-W-E */
    private final int index;

    /** Index of the opposite Facing in the VALUES array */
    private final int opposite;

    /** Ordering index for the HORIZONTALS field (S-W-N-E) */
    private final int horizontalIndex;
    private final String name;
    private final XEnumFacing.Axis axis;
    private final XEnumFacing.AxisDirection axisDirection;

    /** Normalized Vector that points in the direction of this Facing */
    private final XVec3i directionVec;
    
    private final EnumFacing vanillaEquivalent;

    /** All facings in D-U-N-S-W-E order */
    public static final XEnumFacing[] VALUES = new XEnumFacing[6];

    /** All Facings with horizontal axis in order S-W-N-E */
    private static final XEnumFacing[] HORIZONTALS = new XEnumFacing[4];
    private static final Map NAME_LOOKUP = Maps.newHashMap();

    private XEnumFacing(String p_i17_3_, int p_i17_4_, int p_i17_5_, int p_i17_6_, int p_i17_7_, String p_i17_8_, XEnumFacing.AxisDirection p_i17_9_, XEnumFacing.Axis p_i17_10_, XVec3i p_i17_11_, EnumFacing vanillaEquivalent)
    {
        this.index = p_i17_5_;
        this.horizontalIndex = p_i17_7_;
        this.opposite = p_i17_6_;
        this.name = p_i17_8_;
        this.axis = p_i17_10_;
        this.axisDirection = p_i17_9_;
        this.directionVec = p_i17_11_;
        this.vanillaEquivalent = vanillaEquivalent;
    }

    /**
     * Get the Index of this Facing (0-5). The order is D-U-N-S-W-E
     */
    public int getIndex()
    {
        return this.index;
    }

    /**
     * Get the index of this horizontal facing (0-3). The order is S-W-N-E
     */
    public int getHorizontalIndex()
    {
        return this.horizontalIndex;
    }

    /**
     * Get the AxisDirection of this Facing.
     */
    public XEnumFacing.AxisDirection getAxisDirection()
    {
        return this.axisDirection;
    }

    /**
     * Get the opposite Facing (e.g. DOWN => UP)
     */
    public XEnumFacing getOpposite()
    {
        return VALUES[this.opposite];
    }

    /**
     * Rotate this Facing around the given axis clockwise. If this facing cannot be rotated around the given axis,
     * returns this facing without rotating.
     */
    public XEnumFacing rotateAround(XEnumFacing.Axis axis)
    {
        switch (XEnumFacing.EnumFacing$1.field_179515_a[axis.ordinal()])
        {
            case 1:
                if (this != WEST && this != EAST)
                {
                    return this.rotateX();
                }

                return this;

            case 2:
                if (this != UP && this != DOWN)
                {
                    return this.rotateY();
                }

                return this;

            case 3:
                if (this != NORTH && this != SOUTH)
                {
                    return this.rotateZ();
                }

                return this;

            default:
                throw new IllegalStateException("Unable to get CW facing for axis " + axis);
        }
    }

    /**
     * Rotate this Facing around the Y axis clockwise (NORTH => EAST => SOUTH => WEST => NORTH)
     */
    public XEnumFacing rotateY()
    {
        switch (XEnumFacing.EnumFacing$1.field_179513_b[this.ordinal()])
        {
            case 1:
                return EAST;

            case 2:
                return SOUTH;

            case 3:
                return WEST;

            case 4:
                return NORTH;

            default:
                throw new IllegalStateException("Unable to get Y-rotated facing of " + this);
        }
    }

    /**
     * Rotate this Facing around the X axis (NORTH => DOWN => SOUTH => UP => NORTH)
     */
    private XEnumFacing rotateX()
    {
        switch (XEnumFacing.EnumFacing$1.field_179513_b[this.ordinal()])
        {
            case 1:
                return DOWN;

            case 2:
            case 4:
            default:
                throw new IllegalStateException("Unable to get X-rotated facing of " + this);

            case 3:
                return UP;

            case 5:
                return NORTH;

            case 6:
                return SOUTH;
        }
    }

    /**
     * Rotate this Facing around the Z axis (EAST => DOWN => WEST => UP => EAST)
     */
    private XEnumFacing rotateZ()
    {
        switch (XEnumFacing.EnumFacing$1.field_179513_b[this.ordinal()])
        {
            case 2:
                return DOWN;

            case 3:
            default:
                throw new IllegalStateException("Unable to get Z-rotated facing of " + this);

            case 4:
                return UP;

            case 5:
                return EAST;

            case 6:
                return WEST;
        }
    }

    /**
     * Rotate this Facing around the Y axis counter-clockwise (NORTH => WEST => SOUTH => EAST => NORTH)
     */
    public XEnumFacing rotateYCCW()
    {
        switch (XEnumFacing.EnumFacing$1.field_179513_b[this.ordinal()])
        {
            case 1:
                return WEST;

            case 2:
                return NORTH;

            case 3:
                return EAST;

            case 4:
                return SOUTH;

            default:
                throw new IllegalStateException("Unable to get CCW facing of " + this);
        }
    }

    /**
     * Returns a offset that addresses the block in front of this facing.
     */
    public int getFrontOffsetX()
    {
        return this.axis == XEnumFacing.Axis.X ? this.axisDirection.getOffset() : 0;
    }

    public int getFrontOffsetY()
    {
        return this.axis == XEnumFacing.Axis.Y ? this.axisDirection.getOffset() : 0;
    }

    /**
     * Returns a offset that addresses the block in front of this facing.
     */
    public int getFrontOffsetZ()
    {
        return this.axis == XEnumFacing.Axis.Z ? this.axisDirection.getOffset() : 0;
    }

    /**
     * Same as getName, but does not override the method from Enum.
     */
    public String getName2()
    {
        return this.name;
    }

    public XEnumFacing.Axis getAxis()
    {
        return this.axis;
    }

    /**
     * Get the facing specified by the given name
     */
    public static XEnumFacing byName(String name)
    {
        return name == null ? null : (XEnumFacing)NAME_LOOKUP.get(name.toLowerCase());
    }

    /**
     * Get a Facing by it's index (0-5). The order is D-U-N-S-W-E. Named getFront for legacy reasons.
     */
    public static XEnumFacing getFront(int index)
    {
        return VALUES[MathHelper.abs_int(index % VALUES.length)];
    }

    /**
     * Get a Facing by it's horizontal index (0-3). The order is S-W-N-E.
     */
    public static XEnumFacing getHorizontal(int p_176731_0_)
    {
        return HORIZONTALS[MathHelper.abs_int(p_176731_0_ % HORIZONTALS.length)];
    }

    /**
     * Get the Facing corresponding to the given angle (0-360). An angle of 0 is SOUTH, an angle of 90 would be WEST.
     */
    public static XEnumFacing fromAngle(double angle)
    {
        return getHorizontal(MathHelper.floor_double(angle / 90.0D + 0.5D) & 3);
    }

    /**
     * Choose a random Facing using the given Random
     */
    public static XEnumFacing random(Random rand)
    {
        return values()[rand.nextInt(values().length)];
    }

    public static XEnumFacing getFacingFromVector(float p_176737_0_, float p_176737_1_, float p_176737_2_)
    {
    	XEnumFacing enumfacing = NORTH;
        float f = Float.MIN_VALUE;

        for (XEnumFacing enumfacing1 : values())
        {
            float f1 = p_176737_0_ * (float)enumfacing1.directionVec.getX() + p_176737_1_ * (float)enumfacing1.directionVec.getY() + p_176737_2_ * (float)enumfacing1.directionVec.getZ();

            if (f1 > f)
            {
                f = f1;
                enumfacing = enumfacing1;
            }
        }

        return enumfacing;
    }

    public String toString()
    {
        return this.name;
    }

    public String getName()
    {
        return this.name;
    }

    public static XEnumFacing func_181076_a(XEnumFacing.AxisDirection p_181076_0_, XEnumFacing.Axis p_181076_1_)
    {
        for (XEnumFacing enumfacing : values())
        {
            if (enumfacing.getAxisDirection() == p_181076_0_ && enumfacing.getAxis() == p_181076_1_)
            {
                return enumfacing;
            }
        }

        throw new IllegalArgumentException("No such direction: " + p_181076_0_ + " " + p_181076_1_);
    }

    /**
     * Get a normalized Vector that points in the direction of this Facing.
     */
    public XVec3i getDirectionVec()
    {
        return this.directionVec;
    }

    static {
        for (XEnumFacing enumfacing : values())
        {
            VALUES[enumfacing.index] = enumfacing;

            if (enumfacing.getAxis().isHorizontal())
            {
                HORIZONTALS[enumfacing.horizontalIndex] = enumfacing;
            }

            NAME_LOOKUP.put(enumfacing.getName2().toLowerCase(), enumfacing);
        }
    }

    static final class EnumFacing$1 {
        static final int[] field_179515_a;
        static final int[] field_179513_b;
        static final int[] field_179514_c = new int[XEnumFacing.Plane.values().length];

        static {
            try {
                field_179514_c[XEnumFacing.Plane.HORIZONTAL.ordinal()] = 1;
            }
            catch (NoSuchFieldError var11)
            {
                ;
            }

            try {
                field_179514_c[XEnumFacing.Plane.VERTICAL.ordinal()] = 2;
            }
            catch (NoSuchFieldError var10)
            {
                ;
            }

            field_179513_b = new int[XEnumFacing.values().length];

            try {
                field_179513_b[XEnumFacing.NORTH.ordinal()] = 1;
            }
            catch (NoSuchFieldError var9)
            {
                ;
            }

            try {
                field_179513_b[XEnumFacing.EAST.ordinal()] = 2;
            }
            catch (NoSuchFieldError var8)
            {
                ;
            }

            try {
                field_179513_b[XEnumFacing.SOUTH.ordinal()] = 3;
            }
            catch (NoSuchFieldError var7)
            {
                ;
            }

            try {
                field_179513_b[XEnumFacing.WEST.ordinal()] = 4;
            }
            catch (NoSuchFieldError var6)
            {
                ;
            }

            try {
                field_179513_b[XEnumFacing.UP.ordinal()] = 5;
            }
            catch (NoSuchFieldError var5)
            {
                ;
            }

            try {
                field_179513_b[XEnumFacing.DOWN.ordinal()] = 6;
            }
            catch (NoSuchFieldError var4)
            {
                ;
            }

            field_179515_a = new int[XEnumFacing.Axis.values().length];

            try {
                field_179515_a[XEnumFacing.Axis.X.ordinal()] = 1;
            }
            catch (NoSuchFieldError var3)
            {
                ;
            }

            try {
                field_179515_a[XEnumFacing.Axis.Y.ordinal()] = 2;
            }
            catch (NoSuchFieldError var2)
            {
                ;
            }

            try {
                field_179515_a[XEnumFacing.Axis.Z.ordinal()] = 3;
            }
            catch (NoSuchFieldError var1)
            {
                ;
            }
        }
    }

    public static enum Axis implements Predicate {
        X("X", 0, "x", XEnumFacing.Plane.HORIZONTAL),
        Y("Y", 1, "y", XEnumFacing.Plane.VERTICAL),
        Z("Z", 2, "z", XEnumFacing.Plane.HORIZONTAL);

        private static final Map NAME_LOOKUP = Maps.newHashMap();
        private final String name;
        private final XEnumFacing.Plane plane;

        private Axis(String p_i14_3_, int p_i14_4_, String p_i14_5_, XEnumFacing.Plane p_i14_6_)
        {
            this.name = p_i14_5_;
            this.plane = p_i14_6_;
        }

        public static XEnumFacing.Axis byName(String name)
        {
            return name == null ? null : (XEnumFacing.Axis)NAME_LOOKUP.get(name.toLowerCase());
        }

        public String getName2()
        {
            return this.name;
        }

        public boolean isVertical()
        {
            return this.plane == XEnumFacing.Plane.VERTICAL;
        }

        public boolean isHorizontal()
        {
            return this.plane == XEnumFacing.Plane.HORIZONTAL;
        }

        public String toString()
        {
            return this.name;
        }

        public boolean apply(XEnumFacing p_apply_1_)
        {
            return p_apply_1_ != null && p_apply_1_.getAxis() == this;
        }

        public XEnumFacing.Plane getPlane()
        {
            return this.plane;
        }

        public String getName()
        {
            return this.name;
        }

        public boolean apply(Object p_apply_1_)
        {
            return this.apply((XEnumFacing)p_apply_1_);
        }

        static {
            for (XEnumFacing.Axis enumfacing$axis : values())
            {
                NAME_LOOKUP.put(enumfacing$axis.getName2().toLowerCase(), enumfacing$axis);
            }
        }
    }

    public static enum AxisDirection {
        POSITIVE("POSITIVE", 0, 1, "Towards positive"),
        NEGATIVE("NEGATIVE", 1, -1, "Towards negative");

        private final int offset;
        private final String description;

        private AxisDirection(String p_i15_3_, int p_i15_4_, int p_i15_5_, String p_i15_6_)
        {
            this.offset = p_i15_5_;
            this.description = p_i15_6_;
        }

        public int getOffset()
        {
            return this.offset;
        }

        public String toString()
        {
            return this.description;
        }
    }

    public static enum Plane implements Predicate, Iterable {
        HORIZONTAL("HORIZONTAL", 0),
        VERTICAL("VERTICAL", 1);

        private Plane(String p_i16_3_, int p_i16_4_)
        {
        }

        public XEnumFacing[] facings()
        {
            switch (XEnumFacing.EnumFacing$1.field_179514_c[this.ordinal()])
            {
                case 1:
                    return new XEnumFacing[] {XEnumFacing.NORTH, XEnumFacing.EAST, XEnumFacing.SOUTH, XEnumFacing.WEST};
                case 2:
                    return new XEnumFacing[] {XEnumFacing.UP, XEnumFacing.DOWN};
                default:
                    throw new Error("Someone\'s been tampering with the universe!");
            }
        }

        public XEnumFacing random(Random rand)
        {
        	XEnumFacing[] aenumfacing = this.facings();
            return aenumfacing[rand.nextInt(aenumfacing.length)];
        }

        public boolean apply(XEnumFacing p_apply_1_)
        {
            return p_apply_1_ != null && p_apply_1_.getAxis().getPlane() == this;
        }

        public Iterator iterator()
        {
            return Iterators.forArray(this.facings());
        }

        public boolean apply(Object p_apply_1_)
        {
            return this.apply((XEnumFacing)p_apply_1_);
        }
    }

	@Override
	public EnumFacing toVanilla() {
		return this.vanillaEquivalent;
	}
}
