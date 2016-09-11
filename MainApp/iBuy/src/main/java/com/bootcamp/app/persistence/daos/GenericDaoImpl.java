package com.bootcamp.app.persistence.daos;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.bootcamp.app.persistence.daos.interfaces.GenericDAO;
import com.bootcamp.app.utils.HibernateUtil;

public abstract class GenericDaoImpl<T, PK extends Serializable> implements GenericDAO<T, PK> {

	protected Session getSession() {
		return HibernateUtil.getSession();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public PK save(T entity) {
		return (PK) getSession().save(entity);
	}

	@Override
	public void update(T entity) {
		getSession().update(entity);
	}

	@Override
	public void delete(T entity) {
		getSession().delete(entity);
	}

	@Override
	public T findById(Class<T> arg, PK id) {
		return (T) getSession().get(arg, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findOne(Query query) {
		T t;
        t = (T) query.uniqueResult();
        return t;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findMany(Query query) {
		List<T> t;
        t = (List<T>) query.list();
        return t;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(Class<T> arg) {
		return getSession().createCriteria(arg).list();
	}
}