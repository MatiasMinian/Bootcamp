package com.bootcamp.app.persistance.daos;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import com.bootcamp.app.persistance.HibernateUtil;

public abstract class GenericDAO<T, PK extends Serializable> {
	
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
	
	public void update(T entity) {
        getSession().update(entity);
    }
 
    public void delete(T entity) {
        getSession().delete(entity);
    } 
    
    public T findById(PK id) {
    	return (T) getSession().get(type, id);
    }
    
	/*
    public List<T> findMany(Query query) {
        List<T> t;
        t = (List<T>) query.list();
        return t;
    }
 
    public T findOne(Query query) {
        T t;
        t = (T) query.uniqueResult();
        return t;
    }
 
    public List findAll(Class clazz) {
        Session hibernateSession = this.getSession();
        List T = null;
        Query query = hibernateSession.createQuery("from " + clazz.getName());
        T = query.list();
        return T;
    }
    */
}
