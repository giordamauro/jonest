package com.jonest.printing.impl;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.jonest.printing.Printable;
import com.jonest.printing.PrintableList;
import com.jonest.printing.PrintableMap;
import com.jonest.printing.PrintablePrimitive;
import com.jonest.printing.PrintingAdapter;
import com.jonest.util.ReflectionUtils;

public class PrintingAdapterImpl implements PrintingAdapter {

	@Override
	public PrintableMap getPrintableMap(Map<String, Object> mapSource, int depth) {

		PrintableMap printableMap = null;

		if (depth == 0) {
			printableMap = PrintableMap.EMPTY_MAP;
		} else {
			int newDepth = depth - 1;

			printableMap = new PrintableMap();

			for (Entry<String, Object> element : mapSource.entrySet()) {
				String key = element.getKey();
				Printable value = null;

				if (key.equals("class")) {
					value = PrintableMap.EMPTY_MAP;
				} else {
					value = getPrintableObject(element.getValue(), newDepth);
				}
				printableMap.put(key, value);
			}
		}

		return printableMap;
	}

	@Override
	public PrintableList getPrintableList(List<Object> listSource, int depth) {

		PrintableList printableList = null;

		if (depth == 0) {
			printableList = PrintableList.EMPTY_LIST;
		} else {
			int newDepth = depth - 1;

			printableList = new PrintableList();

			for (Object element : listSource) {

				Printable printable = getPrintableObject(element, newDepth);
				printableList.add(printable);
			}
		}

		return printableList;
	}

	@Override
	public Printable getPrintableObject(Object source, int depth) {

		Printable printable = null;

		if (source == null || ReflectionUtils.isPrimitive(source)) {

			printable = new PrintablePrimitive(source);

		} else if (source instanceof Map) {

			@SuppressWarnings("unchecked")
			Map<String, Object> mapSource = (Map<String, Object>) source;
			printable = getPrintableMap(mapSource, depth);

		} else if (source instanceof List) {
			@SuppressWarnings("unchecked")
			List<Object> listSource = (List<Object>) source;
			printable = getPrintableList(listSource, depth);
		} else {

			Map<String, Object> mapSource = getMapFromDto(source);
			printable = getPrintableMap(mapSource, depth);
		}

		return printable;
	}

	private Map<String, Object> getMapFromDto(Object dto) {

		Map<String, Object> map = new HashMap<String, Object>();

		Class<?> sourceClass = dto.getClass();
		Map<String, Method> fieldMethods = ReflectionUtils.getFieldMethods(sourceClass, "get");

		for (Entry<String, Method> entry : fieldMethods.entrySet()) {

			String fieldName = entry.getKey();
			Method method = entry.getValue();
			Object value = null;
			try {
				value = method.invoke(dto);
			} catch (Exception e) {
				throw new IllegalStateException(String.format("Error trying to access field '%s' from method '%s' in class '%s'", fieldName, method.getName(), sourceClass), e);
			}

			map.put(fieldName, value);
		}

		return map;
	}
}
