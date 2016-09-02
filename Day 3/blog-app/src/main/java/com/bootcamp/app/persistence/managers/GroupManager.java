package com.bootcamp.app.persistence.managers;

import org.hibernate.HibernateException;

import com.bootcamp.app.model.Group;
import com.bootcamp.app.persistence.HibernateUtil;
import com.bootcamp.app.persistence.daos.interfaces.IGroupDAO;

public class GroupManager {

	// TODO Should I use Dependency Injection
	private IGroupDAO groupDAO;

	public void saveNewGroup(Group group) {
		try {
			HibernateUtil.beginTransaction();
			groupDAO.save(group);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}
	}

	public void updateGroup(Group group) {
		try {
			HibernateUtil.beginTransaction();
			groupDAO.update(group);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}
	}

	public void deleteGroup(Group group) {
		try {
			HibernateUtil.beginTransaction();
			groupDAO.delete(group);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}
	}

	public Group findGroupById(Long id) {
		Group group = null;
		try {
			HibernateUtil.beginTransaction();
			group = (Group) groupDAO.findById(id);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return group;
	}
}
