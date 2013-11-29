package com.jonest.format.impl;

import com.google.gson.Gson;
import com.jonest.format.ContentFormatter;
import com.jonest.format.FormattedContent;

public class JsonFormatter implements ContentFormatter {

	private final Gson gson;

	public JsonFormatter(Gson gson) {
		this.gson = gson;
	}

	@Override
	public FormattedContent formatObject(Object object) {

		String json = gson.toJson(object);

		FormattedContent formatted = new FormattedContent(json, "application/json");
		return formatted;
	}

}
