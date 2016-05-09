package com.smartosc.demo.hibernate;

import org.hibernate.Query;

import java.util.List;

/**
 * Created by smartosc on 5/9/2016.
 */
public interface GenericDAO<T> {

    void save(T t) throws Exception;

    void merge(T t) throws Exception;

    void delete(T t) throws Exception;

    T findOne(Query query) throws Exception;

    T findById(Class clazz, Integer id) throws Exception;

    List<T> findMany(Query query) throws Exception;

    List<T> findAll(Class clazz) throws Exception;

}
