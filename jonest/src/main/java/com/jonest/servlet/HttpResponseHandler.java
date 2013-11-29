package com.jonest.servlet;

import javax.servlet.http.HttpServletResponse;

public interface HttpResponseHandler {

	void handleSuccess(Object successObject, HttpServletResponse response);

	void handleException(Exception ex, HttpServletResponse response);
}
