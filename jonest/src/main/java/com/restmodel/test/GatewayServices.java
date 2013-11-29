package com.restmodel.test;

public class GatewayServices {

	private final Apis apis;

	private final Caches caches;

	public GatewayServices(Apis apis, Caches caches) {
		this.apis = apis;
		this.caches = caches;
	}

	public Apis getApis() {
		return apis;
	}

	public Caches getCaches() {
		return caches;
	}
}
