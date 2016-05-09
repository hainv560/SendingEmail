package com.smartosc.demo.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by smartosc on 5/7/2016.
 */
public class HibernateUtil {
    private static final SessionFactory SESSION_FACTORY;
    private static final ServiceRegistry SERVICE_REGISTRY;

    static {
        try {
            Configuration configuration = new Configuration().configure();
            SERVICE_REGISTRY = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

            SESSION_FACTORY = configuration.buildSessionFactory(SERVICE_REGISTRY);
        } catch (HibernateException e) {
            System.err.println("Error !");
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

    public static Session getSession() {
        Session session = null;
        try {
            session = SESSION_FACTORY.getCurrentSession();
        } catch (HibernateException e) {
            session = SESSION_FACTORY.openSession();
        }
        return session;
    }

}
