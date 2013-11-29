package com.restmodel.test;

public class ApigeeApis {

	private final GatewayServices gatewayServices;

	private final DeveloperServices developerServices;

	public ApigeeApis(GatewayServices gatewayServices, DeveloperServices developerServices) {
		this.gatewayServices = gatewayServices;
		this.developerServices = developerServices;
	}

	public GatewayServices getGatewayServices() {
		return gatewayServices;
	}

	public DeveloperServices getDeveloperServices() {
		return developerServices;
	}
}
