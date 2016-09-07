package com.bootcamp.app.persistence.managers;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bootcamp.app.model.User;
import com.bootcamp.app.persistence.HibernateUtil;
import com.bootcamp.app.persistence.daos.interfaces.UserDAO;

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
		System.out.println("User was saved successfully");
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
		System.out.println("User was updated successfully");
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
		System.out.println("User was deleted successfully");
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
		System.out.println("User was found successfully");
		return user;
	}
	
	/* *** GETTERS & SETTERS *** */

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
}