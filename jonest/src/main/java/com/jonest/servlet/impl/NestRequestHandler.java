package com.jonest.servlet.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jonest.nest.NestedObjectHandler;
import com.jonest.servlet.HttpRequestHandler;

public class NestRequestHandler implements HttpRequestHandler {

	private final Map<String, Object> baseMap;

	private NestedObjectHandler nestedObjectHandler;

	public NestRequestHandler(NestedObjectHandler nestedObjectHandler, Map<String, Object> baseMap) {
		this.baseMap = baseMap;
		this.nestedObjectHandler = nestedObjectHandler;
	}

	@Override
	public Object handleRequest(HttpServletRequest request) throws Exception {

		String pathInfo = request.getPathInfo();
		List<String> direction = getDirectionFromPath(pathInfo);

		Object result = nestedObjectHandler.getNestedObject(baseMap, direction);

		return result;
	}

	private List<String> getDirectionFromPath(String pathInfo) {

		List<String> pathValues = new ArrayList<String>();
		String[] values = pathInfo.split("/");
		for (String value : values) {
			if (!value.equals("")) {
				pathValues.add(value);
			}
		}
		return pathValues;
	}
}
