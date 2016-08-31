package com.bootcamp.app.persistance.daos;

import com.bootcamp.app.Group;

public class GroupDAO extends GenericDAO<Group, Long> {

	public GroupDAO(Class<Group> type) {
		super(type);
	}
}
