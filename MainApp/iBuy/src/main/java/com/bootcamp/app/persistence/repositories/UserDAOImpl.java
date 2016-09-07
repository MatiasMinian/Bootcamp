package com.bootcamp.app.persistence.repositories;

import org.springframework.stereotype.Repository;

import com.bootcamp.app.model.User;
import com.bootcamp.app.persistence.repositories.interfaces.UserDAO;

@Repository
public class UserDAOImpl extends GenericDaoImpl<User, Long> implements UserDAO {

}
