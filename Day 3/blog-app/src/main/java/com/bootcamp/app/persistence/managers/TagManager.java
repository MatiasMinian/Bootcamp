package com.bootcamp.app.persistence.managers;

import org.hibernate.HibernateException;

import com.bootcamp.app.model.Tag;
import com.bootcamp.app.persistence.HibernateUtil;
import com.bootcamp.app.persistence.daos.interfaces.ITagDAO;

public class TagManager {

	// TODO Should I use Dependency Injection
	private ITagDAO tagDAO;

	public void saveNewTag(Tag tag) {
		try {
			HibernateUtil.beginTransaction();
			tagDAO.save(tag);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}
	}

	public void updateTag(Tag tag) {
		try {
			HibernateUtil.beginTransaction();
			tagDAO.update(tag);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}
	}

	public void deleteTag(Tag tag) {
		try {
			HibernateUtil.beginTransaction();
			tagDAO.delete(tag);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
			HibernateUtil.rollbackTransaction();
		}
	}

	public Tag findTagById(Long id) {
		Tag tag = null;
		try {
			HibernateUtil.beginTransaction();
			tag = (Tag) tagDAO.findById(id);
			HibernateUtil.commitTransaction();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return tag;
	}
}
