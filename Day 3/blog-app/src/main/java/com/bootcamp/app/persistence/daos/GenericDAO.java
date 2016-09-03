package com.bootcamp.app.persistence.daos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import com.bootcamp.app.persistence.HibernateUtil;
import com.bootcamp.app.persistence.daos.interfaces.IGenericDAO;

public abstract class GenericDAO<T, PK extends Serializable> implements IGenericDAO<T, PK> {

	private Class<T> type;

	public GenericDAO(Class<T> type) {
		this.type = type;
	}

	protected Session getSession() {
		return HibernateUtil.getSession();
	}

	@Override
	public T save(T entity) {
		getSession().save(entity);
		return entity;
	}

	@Override
	public T update(T entity) {
		getSession().update(entity);
        return entity;
	}

	@Override
	public void delete(T entity) {
		getSession().delete(entity);
	}

	@Override
	public T findById(PK id) {
		return (T) getSession().get(type, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findOne(Query query) {
		T t;
        t = (T) query.getSingleResult();
        return t;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findMany(Query query) {
		List<T> t;
        t = (List<T>) query.getResultList();
        return t;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		return getSession().createCriteria(type).list();
	}
}