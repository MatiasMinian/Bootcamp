package com.bootcamp.app.persistence.managers;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bootcamp.app.model.User;
import com.bootcamp.app.persistence.daos.interfaces.UserDAO;
import com.bootcamp.app.utils.HibernateUtil;

@Component
public class UserManager {

	@Autowired
	UserDAO userDAO;
	
	/* *** CONSTRUCTORS *** */

	public UserManager() {
	}

	public UserManager(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	/* *** METHODS *** */

	public Long saveNewUser(User user) {
		Long id = null;
		try {
			HibernateUtil.beginTransaction();
			id = userDAO.save(user);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}
		return id;
	}

	public void updateUser(User user) {
		try {
			HibernateUtil.beginTransaction();
			userDAO.update(user);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}
	}

	public void deleteUser(User user) {
		try {
			HibernateUtil.beginTransaction();
			userDAO.delete(user);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}
	}

	public User findUserById(Long id) {
		User user = null;
		try {
			HibernateUtil.beginTransaction();
			user = (User) userDAO.findById(User.class, id);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public List<User> findAllUsers() {
		List<User> users = new ArrayList<>();
		try {
			HibernateUtil.beginTransaction();
			users.addAll(userDAO.findAll(User.class));
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	/* *** GETTERS & SETTERS *** */

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
}