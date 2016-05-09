package com.smartosc.demo.services;

/**
 * Created by smartosc on 5/5/2016.
 */
public interface UserServiceDAO {
    void resetPassword(String email)throws Exception;
}
