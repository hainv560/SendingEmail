package com.smartosc.demo.email.content;

/**
 * Created by smartosc on 5/5/2016.
 */
public class EmailContent {
    private StringBuilder subject = new StringBuilder();
    private StringBuilder header = new StringBuilder();
    private StringBuilder body = new StringBuilder();
    private StringBuilder footer = new StringBuilder();

    public StringBuilder getSubject() {
        return subject;
    }

    public StringBuilder getHeader() {
        return header;
    }


    public StringBuilder getBody() {
        return body;
    }

    public StringBuilder getFooter() {
        return footer;
    }
}
