package com.bootcamp.app.persistence.daos.interfaces;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;

public interface GenericDAO<T, PK extends Serializable> {

	public PK save(T entity);

	public void update(T entity);

	public void delete(T entity);

	public T findById(Class<T> arg, PK id);

	public T findOne(Query query);

	public List<T> findMany(Query query);

	public List<T> findAll(Class<T> arg);
}
