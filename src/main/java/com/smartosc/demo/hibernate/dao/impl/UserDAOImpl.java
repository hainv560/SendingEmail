package com.smartosc.demo.hibernate.dao.impl;

import com.smartosc.demo.hibernate.GenericDAOImpl;
import com.smartosc.demo.hibernate.dao.UserDAO;
import com.smartosc.demo.model.User;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by smartosc on 5/7/2016.
 */
public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO {


    public User findUserByEmail(String email) throws Exception {
        Session session = this.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from User u where u.email = :email");
            query.setParameter("email", email);
            return findOne(query);
        } catch (Exception e) {
            session.getTransaction().rollback();
            session.clear();
            throw e;
        } finally {
            session.getTransaction().commit();
        }

    }

}
