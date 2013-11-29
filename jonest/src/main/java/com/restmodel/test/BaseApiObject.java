package com.restmodel.test;

import java.util.List;
import java.util.Map;

public class BaseApiObject {

	private String name;

	private List<Integer> revision;

	private Map<String, BaseApiObject> map;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Integer> getRevision() {
		return revision;
	}

	public void setRevision(List<Integer> revision) {
		this.revision = revision;
	}

	public Map<String, BaseApiObject> getMap() {
		return map;
	}

	public void setMap(Map<String, BaseApiObject> map) {
		this.map = map;
	}
}
