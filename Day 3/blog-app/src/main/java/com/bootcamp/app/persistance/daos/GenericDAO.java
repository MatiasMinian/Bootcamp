package com.bootcamp.app.persistance.daos;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.bootcamp.app.persistance.HibernateUtil;

public abstract class GenericDAO<T, PK extends Serializable> {
	
	private Class<T> type;
	
	public GenericDAO(Class<T> type) {
		this.type = type;
	}
	
	protected Session getSession() {
        return HibernateUtil.getSession();
    }
	
	public PK save(T entity) {
		return (PK) getSession().save(entity);		
	}
	
	public T read(PK id) {
        return (T) getSession().get(type, id);
    }
	
	public void update(T o) {
        getSession().update(o);
    }
 
    public void delete(T o) {
        getSession().delete(o);
    } 
	
	/*
    public void save(T entity) {
        Session hibernateSession = this.getSession();
        hibernateSession.saveOrUpdate(entity);
    }
 
    public void merge(T entity) {
        Session hibernateSession = this.getSession();
        hibernateSession.merge(entity);
    }
 
    public void delete(T entity) {
        Session hibernateSession = this.getSession();
        hibernateSession.delete(entity);
    }
 
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
 
    public T findByID(Class clazz, ID id) {
        Session hibernateSession = this.getSession();
        T t = null;
        t = (T) hibernateSession.get(clazz, id);
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
