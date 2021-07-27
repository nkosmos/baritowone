package baritonex.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Optional;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.util.IStringSerializable;

public class PropertyHelper {
	
	private static Map<PropertyEnum, Map<String, Object>> nameToValueCache = new HashMap<>();
	
	public static Optional parseValue(IProperty property, String string){
		if(property instanceof PropertyBool) {
			return Optional.of(Boolean.parseBoolean(string));
		}
		if(property instanceof PropertyInteger) {
			Collection<Integer> allowed = property.getAllowedValues();
			try {
				Integer val = Integer.valueOf(string);
				return allowed.contains(val) ? Optional.of(val) : Optional.absent();
			} catch(NumberFormatException e) {
				return Optional.absent();
			}
		}
		if(property instanceof PropertyEnum) {
			PropertyEnum p = (PropertyEnum)property;
			if(!nameToValueCache.containsKey(p)) {
				Map<String, Object> map = new HashMap<>();
				for(Object o : p.getAllowedValues()) {
					if(o instanceof IStringSerializable) {
						map.put(((IStringSerializable)o).getName(), o);
					}
				}
				nameToValueCache.put(p, map);
			}
			return Optional.fromNullable(nameToValueCache.get(p).get(string));
		}
		
		return Optional.absent();
	}
	
}
