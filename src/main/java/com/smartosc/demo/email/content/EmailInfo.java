package com.smartosc.demo.email.content;

import java.util.Observable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smartosc on 5/5/2016.
 */
public class EmailInfo extends Observable {
    private List<String> listSend = new ArrayList<String>();
    private List<String> listCC = new ArrayList<String>();
    private List<String> listBCC = new ArrayList<String>();
    private EmailContent content;

    public List<String> getListSend() {
        return listSend;
    }

    public void setListSend(List<String> listSend) {
        this.listSend = listSend;
    }

    public List<String> getListCC() {
        return listCC;
    }

    public void setListCC(List<String> listCC) {
        this.listCC = listCC;
    }

    public List<String> getListBCC() {
        return listBCC;
    }

    public void setListBCC(List<String> listBCC) {
        this.listBCC = listBCC;
    }

    public EmailContent getContent() {
        return content;
    }

    public void setContent(EmailContent content) {
        this.content = content;
    }
}
