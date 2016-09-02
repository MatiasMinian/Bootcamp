package com.bootcamp.app.persistence.daos;

import com.bootcamp.app.model.Group;
import com.bootcamp.app.persistence.daos.interfaces.IGroupDAO;

public class GroupDAO extends GenericDAO<Group, Long> implements IGroupDAO {

	public GroupDAO(Class<Group> type) {
		super(type);
	}
}
