package com.bootcamp.app.persistence.managers;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;

import com.bootcamp.app.model.User;
import com.bootcamp.app.persistence.HibernateUtil;
import com.bootcamp.app.persistence.daos.UserDAO;

public class UserManager {
	
	@Autowired
	private UserDAO userDAO;
	
	/* *** CONSTRUCTORS *** */
	
	public UserManager(){}
	
	public UserManager(UserDAO userDAO) {
		this.userDAO = userDAO;		
	}
	
	/* *** METHODS *** */
	
	public void saveNewUser(User user) {
		try {
			HibernateUtil.beginTransaction();
			userDAO.save(user);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}
	}
	
	public void updateUser(User user) {
		try {
			HibernateUtil.beginTransaction();
			userDAO.save(user);
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
			user = (User) userDAO.findOne(id);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
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