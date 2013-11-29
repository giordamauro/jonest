package com.jonest.servlet;

import javax.servlet.http.HttpServletRequest;

public interface HttpRequestHandler {

	Object handleRequest(HttpServletRequest request) throws Exception;
}
