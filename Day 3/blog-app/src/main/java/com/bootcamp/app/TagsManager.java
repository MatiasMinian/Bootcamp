package com.bootcamp.app;

import java.util.HashSet;
import java.util.Set;

public class TagsManager {

	private static TagsManager tagsManager = null;

	private Set<Tag> tags = new HashSet<>();

	public static TagsManager getInstance() {
		if (tagsManager == null) {
			tagsManager = new TagsManager();
		}
		return tagsManager;
	}

	/* *** GETTERS & SETTERS *** */

	public void addTags(Set<Tag> tags) {
		if (tags != null) {
			this.tags.addAll(tags);
		}
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
}
