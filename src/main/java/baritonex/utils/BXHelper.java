package baritonex.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import net.minecraft.util.LongHashMap;

public class BXHelper {

	private static final List<Field> lhmFields = new ArrayList<>();

	public static LongHashMap copy(LongHashMap longHashMap) throws ReflectiveOperationException {
		LongHashMap newHashMap = new LongHashMap();

		if (lhmFields.isEmpty()) {
			Stream.of(LongHashMap.class.getDeclaredFields()).filter(f -> !Modifier.isFinal(f.getModifiers()))
					.forEach(lhmFields::add);
			lhmFields.stream().filter(f -> !f.isAccessible()).forEach(f -> f.setAccessible(true));
		}

		for (Field field : lhmFields) {
			if (field.getType().isArray()) {
				Object[] objects = (Object[]) field.get(longHashMap);
				field.set(newHashMap, Arrays.copyOf(objects, objects.length));
			} else {
				field.set(newHashMap, field.get(longHashMap));
			}
		}
		return newHashMap;
	}

}
