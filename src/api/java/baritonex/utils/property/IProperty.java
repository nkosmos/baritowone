package baritonex.utils.property;

import java.util.Collection;

public interface IProperty<T extends Comparable<T>> {
	
    String getName();
    
    Collection<T> getAllowedValues();
    
    Class<T> getValueClass();
    
    String getName(final T p0);
}
 