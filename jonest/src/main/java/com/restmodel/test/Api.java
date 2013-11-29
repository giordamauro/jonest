package com.restmodel.test;

import java.util.Map;

public class Api {

	private Map<Integer, Revision> revisions;
	private String name;

	public Api(Map<Integer, Revision> revisions, String name) {
		this.revisions = revisions;
		this.name = name;
	}

	public Map<Integer, Revision> getRevisions() {
		return revisions;
	}

	public String getName() {
		return name;
	}

	public void createRevision(Revision revision) {
		revisions.put(revision.getId(), revision);
	}

	public void deleteRevision(Integer revisionId) {
		revisions.remove(revisionId);
	}

	public void update(Api api) {
		this.name = api.name;
		this.revisions = api.revisions;
	}
}
