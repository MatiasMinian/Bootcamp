package com.bootcamp.app.persistence.daos.interfaces;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

public interface IGenericDAO<T, PK extends Serializable> {
	
	public T save(T entity);
	
	public T update(T entity);
	
	public void delete(T entity);
	
	public T findById(PK id);
	
	public T findOne(Query query);
	
	public List<T> findMany(Query query);
	
	public List<T> findAll();
}
