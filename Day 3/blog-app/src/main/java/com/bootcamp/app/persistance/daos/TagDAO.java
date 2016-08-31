package com.bootcamp.app.persistance.daos;

import com.bootcamp.app.Tag;

public class TagDAO extends GenericDAO<Tag, Long> {

	public TagDAO(Class<Tag> type) {
		super(type);
	}
}
