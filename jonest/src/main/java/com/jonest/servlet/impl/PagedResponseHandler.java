package com.jonest.servlet.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.jonest.adapters.ObjectAdapter;
import com.jonest.format.ContentFormatter;
import com.jonest.format.FormattedContent;
import com.jonest.printing.Printable;
import com.jonest.servlet.HttpResponseHandler;

public class PagedResponseHandler implements HttpResponseHandler {

	private final ObjectAdapter<Object, Printable> printableAdapter;

	private final ObjectAdapter<Object, Object> pagingHandler;

	private final ContentFormatter contentFormatter;

	public PagedResponseHandler(ObjectAdapter<Object, Printable> printableAdapter, ObjectAdapter<Object, Object> pagingHandler, ContentFormatter contentFormatter) {
		this.printableAdapter = printableAdapter;
		this.pagingHandler = pagingHandler;
		this.contentFormatter = contentFormatter;
	}

	@Override
	public void handleSuccess(Object successObject, HttpServletResponse response) {

		Printable printable = printableAdapter.adapt(successObject);

		Object pagedObject = pagingHandler.adapt(printable);

		FormattedContent content = contentFormatter.formatObject(pagedObject);
		response.setContentType(content.getContentType());
		try {
			response.getWriter().write(content.getContent());
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public void handleException(Exception ex, HttpServletResponse response) {
		try {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

}
