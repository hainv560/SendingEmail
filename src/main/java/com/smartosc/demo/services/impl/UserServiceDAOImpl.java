package com.smartosc.demo.services.impl;

import com.smartosc.demo.common.Constants;
import com.smartosc.demo.email.EmailTopic;
import com.smartosc.demo.email.EmailTopicImpl;
import com.smartosc.demo.email.content.EmailContent;
import com.smartosc.demo.email.content.EmailInfo;
import com.smartosc.demo.hibernate.HibernateUtil;
import com.smartosc.demo.hibernate.dao.UserDAO;
import com.smartosc.demo.hibernate.dao.impl.UserDAOImpl;
import com.smartosc.demo.model.User;
import com.smartosc.demo.services.UserServiceDAO;
import com.smartosc.demo.util.EmailValidator;
import com.smartosc.demo.util.ResourceBundleUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.Session;

import java.text.MessageFormat;

/**
 * Created by smartosc on 5/5/2016.
 */
public class UserServiceDAOImpl implements UserServiceDAO {
    private EmailTopic topic;
    private static ResourceBundleUtil bundle = ResourceBundleUtil.getInstance();
    private UserDAO userDAO;

    public UserServiceDAOImpl() throws Exception {
        topic = new EmailTopicImpl();
        userDAO = new UserDAOImpl();
    }


    public void resetPassword(String email) throws Exception {
        User user = null;
        try {
            user = userDAO.findUserByEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (user == null)
            throw new Exception("Email not exist");
        String password = RandomStringUtils.randomAlphabetic(6);
        try {
            user.setPassword(password);
            userDAO.merge(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        EmailInfo info = new EmailInfo();
        info.getListSend().add(email);

        EmailContent content = new EmailContent();
        content.getSubject().append(bundle.loadResource(Constants.EMAIL_SUBJECT_PARAM));
        content.getHeader().append(bundle.loadResource(Constants.EMAIL_HEADER_PARAM));
        content.getBody().append(MessageFormat.format(bundle.loadResource(Constants.EMAIL_BODY_PARAM), email, password));
        content.getFooter().append(bundle.loadResource(Constants.EMAIL_FOOTER_PARAM));
        info.setContent(content);

        topic.setEmailInfo(info);
        topic.notifyObservers();

    }
}
