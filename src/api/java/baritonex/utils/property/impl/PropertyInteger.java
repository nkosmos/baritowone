package baritonex.utils.property.impl;

import java.util.Collection;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import baritonex.utils.property.PropertyHelper;

public class PropertyInteger extends PropertyHelper<Integer>
{
    private final ImmutableSet<Integer> allowedValues;
    
    protected PropertyInteger(final String name, final int min, final int max) {
        super(name, Integer.class);
        if (min < 0) {
            throw new IllegalArgumentException("Min value of " + name + " must be 0 or greater");
        }
        if (max <= min) {
            throw new IllegalArgumentException("Max value of " + name + " must be greater than min (" + min + ")");
        }
        Set<Integer> set = Sets.<Integer>newHashSet();
        for (int i = min; i <= max; ++i) {
            set.add(i);
        }
        this.allowedValues = ImmutableSet.copyOf((Collection<? extends Integer>)set);
    }
    
    @Override
    public Collection<Integer> getAllowedValues() {
        return this.allowedValues;
    }
    
    @Override
    public boolean equals(final Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (p_equals_1_ == null || this.getClass() != p_equals_1_.getClass()) {
            return false;
        }
        if (!super.equals(p_equals_1_)) {
            return false;
        }
        final PropertyInteger propertyinteger = (PropertyInteger)p_equals_1_;
        return this.allowedValues.equals(propertyinteger.allowedValues);
    }
    
    @Override
    public int hashCode() {
        int i = super.hashCode();
        i = 31 * i + this.allowedValues.hashCode();
        return i;
    }
    
    public static PropertyInteger create(final String name, final int min, final int max) {
        return new PropertyInteger(name, min, max);
    }
    
    @Override
    public String getName(final Integer value) {
        return value.toString();
    }
}