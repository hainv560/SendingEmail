package com.smartosc.demo.email;

import com.smartosc.demo.email.content.EmailInfo;

import java.util.Observer;

/**
 * Created by smartosc on 5/6/2016.
 */
public interface EmailTopic {
    void add(Observer obj);

    void remove(Observer obj);

    void notifyObservers();

    EmailInfo getEmailInfo();

    void setEmailInfo(EmailInfo info);
}
