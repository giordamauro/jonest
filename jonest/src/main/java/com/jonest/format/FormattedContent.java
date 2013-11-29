package com.jonest.format;

public class FormattedContent {

	private final String content;

	private final String contentType;

	public FormattedContent(String content, String contentType) {
		this.content = content;
		this.contentType = contentType;
	}

	public String getContent() {
		return content;
	}

	public String getContentType() {
		return contentType;
	}
}
