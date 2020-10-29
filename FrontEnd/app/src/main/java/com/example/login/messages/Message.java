package com.example.login.messages;
import java.util.Date;

public class Message {
    private String idReceiver;
    private String idSender;
    private String uNameReceiver;
    private String uNameSender;
    private Date date;
    private String text;

    public Message(String idReceiver, String idSender, String uNameReceiver, String uNameSender, Date date, String text) {
        this.idReceiver = idReceiver;
        this.idSender = idSender;
        this.uNameReceiver = uNameReceiver;
        this.uNameSender = uNameSender;
        this.date = date;
        this.text = text;
    }

    public String getIdReceiver() {
        return idReceiver;
    }

    public void setIdReceiver(String idReceiver) {
        this.idReceiver = idReceiver;
    }

    public String getIdSender() {
        return idSender;
    }

    public void setIdSender(String idSender) {
        this.idSender = idSender;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getuNameReceiver() {
        return uNameReceiver;
    }

    public void setuNameReceiver(String uNameReceiver) {
        this.uNameReceiver = uNameReceiver;
    }

    public String getuNameSender() {
        return uNameSender;
    }

    public void setuNameSender(String uNameSender) {
        this.uNameSender = uNameSender;
    }

    @Override
    public String toString() {
        return "Message{" + "idReceiver='" + idReceiver + '\'' + ", idSender='" + idSender + '\'' + ", uNameReceiver='" + uNameReceiver + '\'' + ", uNameSender='" + uNameSender + '\'' + ", date=" + date + ", text='" + text + '\'' + '}';
    }
}
