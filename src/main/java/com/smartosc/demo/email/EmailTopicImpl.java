package com.smartosc.demo.email;

import com.smartosc.demo.email.content.EmailInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * Created by smartosc on 5/6/2016.
 */
public class EmailTopicImpl implements EmailTopic {
    private List<Observer> listSender = new ArrayList<Observer>();
    private EmailInfo info;

    public EmailTopicImpl() throws Exception {
        Observer observer = new EmailSender();
        this.add(observer);
    }

    public void add(Observer obj) {
        if (obj == null)
            throw new NullPointerException("Null Observer");
        if (!listSender.contains(obj)) {
            listSender.add(obj);
        }
    }

    public void remove(Observer obj) {
        if (obj == null)
            throw new NullPointerException("Null Observer");
        listSender.remove(obj);
    }

    public void notifyObservers() {
        for (Observer observer : listSender) {
            observer.update(this.getEmailInfo(), null);
        }
    }

    public EmailInfo getEmailInfo() {
        return info;
    }

    public void setEmailInfo(EmailInfo info) {
        if (info == null)
            throw new NullPointerException("Null Observer");
        this.info = info;
    }

}
