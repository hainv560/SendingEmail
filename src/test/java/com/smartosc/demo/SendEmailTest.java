package com.smartosc.demo;

import com.smartosc.demo.email.content.EmailInfo;
import com.smartosc.demo.hibernate.HibernateUtil;
import com.smartosc.demo.model.User;
import com.smartosc.demo.services.UserServiceDAO;
import com.smartosc.demo.services.impl.UserServiceDAOImpl;
import com.smartosc.demo.util.EmailValidator;
import org.hibernate.Session;

/**
 * Created by smartosc on 5/5/2016.
 */
public class SendEmailTest {
    private static UserServiceDAO serviceDAO;

    static {
        try {
            serviceDAO = new UserServiceDAOImpl();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String email = "toemail";
        if (EmailValidator.getInstance().isValid(email)) {
            try {
                serviceDAO.resetPassword(email);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Email invalid");
        }
    }
}
