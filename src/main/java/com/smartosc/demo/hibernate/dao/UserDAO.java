package com.smartosc.demo.hibernate.dao;

import com.smartosc.demo.hibernate.GenericDAO;
import com.smartosc.demo.model.User;

import javax.jws.soap.SOAPBinding;

/**
 * Created by smartosc on 5/7/2016.
 */
public interface UserDAO extends GenericDAO<User> {
    User findUserByEmail(String email) throws Exception;
}
