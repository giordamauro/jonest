package com.jonest.nest.impl;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import com.jonest.nest.NestedObjectHandler;
import com.jonest.util.ReflectionUtils;

public class NestedObjectHandlerImpl implements NestedObjectHandler {

	@Override
	public Object getNestedObject(Object source, List<String> direction) {

		Object parent = source;

		for (String actualDirection : direction) {
			if (parent.getClass().isPrimitive()) {
				throw new IllegalStateException(String.format("Cannot retrieve field '%s' from a primitive value", actualDirection));

			} else if (parent instanceof Map) {
				@SuppressWarnings("unchecked")
				Map<String, ?> map = (Map<String, ?>) parent;
				parent = getFromMap(map, actualDirection);
			} else if (parent instanceof List) {
				List<?> list = (List<?>) parent;
				parent = getFromList(list, actualDirection);
			} else {
				parent = getFromObject(parent, actualDirection);
			}
		}
		return parent;
	}

	private Object getFromObject(Object parent, String value) {
		Class<?> parentClass = parent.getClass();
		Method getMethod = ReflectionUtils.getGetMethod(parentClass, value);

		Object field = null;
		try {
			field = getMethod.invoke(parent);

		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
		return field;
	}

	public Object getFromList(List<?> list, String pathValue) {
		int index = -1;
		try {
			index = Integer.valueOf(pathValue);
		} catch (Exception e) {
			throw new IllegalStateException(String.format("List index must be a number: Couldn't parse '%s'", pathValue));
		}
		if (index > list.size() || index < 1) {
			throw new IllegalStateException(String.format("List index out of bounds: '%s'", index));
		}

		Object object = list.get(index - 1);
		return object;
	}

	private Object getFromMap(Map<String, ?> map, String field) {
		if (!map.containsKey(field)) {
			throw new IllegalStateException(String.format("Field '%s' not found in map", field));
		}
		Object object = map.get(field);
		return object;
	}

}
