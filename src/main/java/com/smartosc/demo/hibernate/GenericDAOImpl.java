package com.smartosc.demo.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by smartosc on 5/9/2016.
 */
public class GenericDAOImpl<T> implements GenericDAO<T> {

    protected Session getSession() {
        return HibernateUtil.getSession();
    }


    public void save(T t) throws Exception {
        Session session = this.getSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(t);
        } catch (Exception e) {
            session.getTransaction().rollback();
            session.clear();
            throw e;
        } finally {
            session.getTransaction().commit();
        }
    }

    public void merge(T t) throws Exception {
        Session session = this.getSession();
        try {
            session.beginTransaction();
            session.merge(t);
        } catch (Exception e) {
            session.getTransaction().rollback();
            session.clear();
            throw e;
        } finally {
            session.getTransaction().commit();
        }
    }

    public void delete(T t) throws Exception {
        Session session = this.getSession();
        try {
            session.beginTransaction();
            session.delete(t);
        } catch (Exception e) {
            session.getTransaction().rollback();
            session.clear();
            throw e;
        } finally {
            session.getTransaction().commit();
        }
    }

    @SuppressWarnings("unchecked")
    public T findById(Class clazz, Integer id) throws Exception {
        T t = null;
        Session session = getSession();
        try {
            session.beginTransaction();
            t = (T) session.get(clazz.getName(), id);
            return t;
        } finally {
            if (session != null)
                session.getTransaction().commit();
        }

    }

    @SuppressWarnings("unchecked")
    public T findOne(Query query) throws Exception {
        T t = null;
        t = (T) query.uniqueResult();
        return t;
    }

    @SuppressWarnings("unchecked")
    public List<T> findMany(Query query) throws Exception {
        List<T> t = null;
        t = (List<T>) query.list();
        return t;
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll(Class clazz) throws Exception {
        List<T> t = null;
        Session session = this.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from " + clazz.getName());
            t = (List<T>) query.list();
            return t;
        } finally {
            if (session != null)
                session.getTransaction().commit();
        }
    }
}