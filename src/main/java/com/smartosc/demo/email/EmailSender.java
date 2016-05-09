package com.smartosc.demo.email;

import com.smartosc.demo.common.Constants;
import com.smartosc.demo.email.content.EmailContent;
import com.smartosc.demo.email.content.EmailInfo;
import com.smartosc.demo.util.EmailValidator;
import com.smartosc.demo.util.ResourceBundleUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.*;

/**
 * Created by smartosc on 5/5/2016.
 */
public class EmailSender implements Observer {
    private ResourceBundleUtil bundle = ResourceBundleUtil.getInstance();
    private final String email;
    private final String password;
    private final String hostParam;
    private final String portParam;
    private final static Logger logger = LogManager.getLogger(EmailSender.class.getName());

    public EmailSender() throws Exception {
        email = bundle.loadResource(Constants.EMAIL_ADDRESS_PARAM);
        password = bundle.loadResource(Constants.EMAIL_PASSWORD_PARAM);
        hostParam = bundle.loadResource(Constants.EMAIL_HOST_PARAM);
        portParam = bundle.loadResource(Constants.EMAIL_PORT_PARAM);

        if (!EmailValidator.getInstance().isValid(email))
            throw new Exception("Email invalid");
        if (StringUtils.isBlank(password)) {
            throw new Exception("Email password invalid");
        }
        if (StringUtils.isBlank(hostParam))
            throw new Exception("Host param invalid");
        if (StringUtils.isBlank(portParam))
            throw new Exception("Port param invalid");
    }

    public void update(Observable o, Object arg) {
        EmailInfo info = (EmailInfo) o;
        EmailContent content = info.getContent();

        Properties properties = new Properties();

        properties.put("mail.smtp.host", hostParam);
        properties.put("mail.smtp.socketFactory.port", portParam);
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", portParam);

        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            InternetAddress addressFrom = new InternetAddress(email);

            // Set from and to of header
            message.setFrom(addressFrom);

            setRecipientsToSendMail(info.getListSend(), message, Message.RecipientType.TO);
            setRecipientsToSendMail(info.getListCC(), message, Message.RecipientType.CC);
            setRecipientsToSendMail(info.getListBCC(), message, Message.RecipientType.BCC);

            String emailSubject = content.getSubject().toString();
            String headerOfContent = content.getHeader().toString();
            String bodyOfContent = content.getBody().toString();
            String footerOfContent = content.getFooter().toString();
            String body = headerOfContent + " <br /> <br /> " + bodyOfContent + " <br /> <br /> " + footerOfContent;

            // Set subject of header
            message.setSubject(emailSubject, "UTF-8");
            message.setSentDate(new Date());

            // Create body part
            MimeBodyPart bodyPart = new MimeBodyPart();
            bodyPart.setContent(body, "text/html;charset=utf-8");

            Multipart multiPart = new MimeMultipart();
            multiPart.addBodyPart(bodyPart);

            String[] attachPath = this.getAttachPath();
            if (attachPath.length > 0) {
                for (String file : attachPath) {
                    addAttachment(multiPart,file);
                }
            }

            // Set content of message
            message.setContent(multiPart);
            Transport.send(message);
            System.out.println("Done");

        } catch (MessagingException e) {
            System.out.println("Error " + e);
        }

    }

    private String[] getAttachPath() {
        String[] filePath = {"D:/girl.jpg","D:/cold.jpg"};
        return filePath;
    }

    private void addAttachment(Multipart multiPart, String filePath) throws MessagingException {
        DataSource source = new FileDataSource(filePath);
        MimeBodyPart attachPart = new MimeBodyPart();

        attachPart.setDataHandler(new DataHandler(source));
        attachPart.setFileName(filePath);
        multiPart.addBodyPart(attachPart);
    }

    private void setRecipientsToSendMail(List<String> list, Message message, Message.RecipientType type) throws MessagingException {
        InternetAddress address[] = new InternetAddress[list.size()];
        for (int i = 0; i < list.size(); i++) {
            address[i] = new InternetAddress(list.get(i));
        }
        message.setRecipients(type, address);
    }
}
