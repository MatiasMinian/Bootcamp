package com.bootcamp.app.persistance.daos;

import com.bootcamp.app.User;

public class UserDAO extends GenericDAO<User, Long> {

	public UserDAO(Class<User> type) {
		super(type);
	}
}
