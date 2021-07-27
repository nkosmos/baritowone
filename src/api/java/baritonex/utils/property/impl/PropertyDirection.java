package baritonex.utils.property.impl;

import java.util.Collection;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import baritonex.utils.data.XEnumFacing;

public class PropertyDirection extends PropertyEnum<XEnumFacing>
{
    protected PropertyDirection(String name, Collection<XEnumFacing> values)
    {
        super(name, XEnumFacing.class, values);
    }

    /**
     * Create a new PropertyDirection with the given name
     */
    public static PropertyDirection create(String name)
    {
        return create(name, Predicates.<XEnumFacing>alwaysTrue());
    }

    /**
     * Create a new PropertyDirection with all directions that match the given Predicate
     */
    public static PropertyDirection create(String name, Predicate<XEnumFacing> filter)
    {
        return create(name, Collections2.<XEnumFacing>filter(Lists.newArrayList(XEnumFacing.values()), filter));
    }

    /**
     * Create a new PropertyDirection for the given direction values
     */
    public static PropertyDirection create(String name, Collection<XEnumFacing> values)
    {
        return new PropertyDirection(name, values);
    }
}
