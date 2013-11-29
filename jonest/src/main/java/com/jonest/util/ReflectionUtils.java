package com.jonest.util;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public final class ReflectionUtils {

	private ReflectionUtils() {
	}

	public static Map<String, Method> getFieldMethods(Class<?> parentClass, String prefix) {

		Map<String, Method> fieldMethods = new HashMap<String, Method>();

		Method[] methods = parentClass.getMethods();
		for (Method method : methods) {
			String methodName = method.getName();
			Class<?>[] parameterTypes = method.getParameterTypes();
			if (methodName.startsWith(prefix) && parameterTypes.length == 0) {
				String field = methodName.replaceFirst(prefix, "");
				String fieldName = field.substring(0, 1).toLowerCase();
				if (field.length() > 1) {
					fieldName += field.substring(1, field.length());
				}
				fieldMethods.put(fieldName, method);
			}
		}
		return fieldMethods;
	}

	public static Method getGetMethod(Class<?> parentClass, String value) {

		Method[] methods = parentClass.getMethods();
		for (Method method : methods) {
			String methodName = method.getName();
			if (methodName.toLowerCase().equals("get" + value)) {
				return method;
			}
		}
		throw new IllegalStateException(String.format("Couldn't find get method for value '%s' in class '%s'", value, parentClass.getName()));
	}

	public static boolean isPrimitive(Object object) {

		Class<?> sourceClass = object.getClass();
		return (sourceClass.isPrimitive() || object instanceof String || object instanceof Integer || object instanceof Long || object instanceof Double || object instanceof Boolean);
	}
}
