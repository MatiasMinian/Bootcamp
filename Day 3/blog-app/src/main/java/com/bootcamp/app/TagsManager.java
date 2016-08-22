package com.bootcamp.app;

import java.util.HashSet;
import java.util.Set;

public class TagsManager {

	private static TagsManager tagsManager = null;

	private Set<String> tags = new HashSet<>();

	public static TagsManager getInstance() {
		if (tagsManager == null) {
			tagsManager = new TagsManager();
		}
		return tagsManager;
	}

	/* *** GETTERS & SETTERS *** */

	public void addTags(Set<String> tags) {
		if (tags != null) {
			this.tags.addAll(tags);
		}
	}

	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}
}
