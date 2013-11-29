package com.restmodel.test;

import java.util.Map;

public class Apis {

	private final Map<String, Api> apis;

	public Apis(Map<String, Api> apis) {
		this.apis = apis;
	}

	public Map<String, Api> getApis() {
		return apis;
	}

	public void createApi(Api api) {
		apis.put(api.getName(), api);
	}

	public void deleteApi(String name) {
		apis.remove(name);
	}
}
