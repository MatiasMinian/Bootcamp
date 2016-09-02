package com.bootcamp.app.persistence.daos;

import com.bootcamp.app.model.User;
import com.bootcamp.app.persistence.daos.interfaces.IUserDAO;

public class UserDAO extends GenericDAO<User, Long> implements IUserDAO {

	public UserDAO(Class<User> type) {
		super(type);
	}
}
