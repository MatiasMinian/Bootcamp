package com.bootcamp.app.persistance.daos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import com.bootcamp.app.persistance.HibernateUtil;

public class GenericDAO<T, PK extends Serializable> {
	
	private Class<T> type;
	
	public GenericDAO(Class<T> type) {
		this.type = type;
	}
	
	protected Session getSession() {
        return HibernateUtil.getSession();
    }
	
	public T save(T entity) {
		getSession().save(entity);	
		return entity;
	}
	
	public T read(PK id) {
        return (T) getSession().get(type, id);
    }
	
	public T update(T entity) {
        getSession().update(entity);
        return entity;
    }
 
    public void delete(T entity) {
        getSession().delete(entity);
    } 
    
    public T findById(PK id) {
    	return (T) getSession().get(type, id);
    }
    
    @SuppressWarnings("unchecked")
	public T findOne(Query query) {
        T t;
        t = (T) query.getSingleResult();
        return t;
    }
    
    @SuppressWarnings("unchecked")
	public List<T> findMany(Query query) {
    	List<T> t;
        t = (List<T>) query.getResultList();
        return t;    	
    }
    
    @SuppressWarnings({ "deprecation", "unchecked" })
	public List<T> findAll() {
		return getSession().createCriteria(type).list();
	}
}
