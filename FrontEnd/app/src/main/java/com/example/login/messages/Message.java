package com.example.login.messages;

import java.util.Date;

public class Message {
    private String idReceiver;
    private String idSender;
    private String uNameReceiver;
    private String uNameSender;
    private Date date;
    private String text;

    /**
     * Constructor for Message.
     *
     * @param idReceiver    returns the idReceiver.
     * @param idSender      returns the idSener.
     * @param uNameReceiver returns the NameReceiver.
     * @param uNameSender   returns NameSender.
     * @param date          returns the date.
     * @param text          returns text
     */
    public Message(String idReceiver, String idSender, String uNameReceiver, String uNameSender, Date date, String text) {
        this.idReceiver = idReceiver;
        this.idSender = idSender;
        this.uNameReceiver = uNameReceiver;
        this.uNameSender = uNameSender;
        this.date = date;
        this.text = text;
    }

    /**
     * It gets the idReceiver.
     *
     * @return gets the idReceiver.
     */
    public String getIdReceiver() {
        return idReceiver;
    }

    /**
     * Sets the idReceiver.
     *
     * @param idReceiver
     */
    public void setIdReceiver(String idReceiver) {
        this.idReceiver = idReceiver;
    }

    /**
     * It gets the Idsender.
     *
     * @return gets the IdSender.
     */
    public String getIdSender() {
        return idSender;
    }

    /**
     * Sets the Idsender
     *
     * @param idSender
     */
    public void setIdSender(String idSender) {
        this.idSender = idSender;
    }

    /**
     * It gets the Date.
     *
     * @return the Date.
     */
    public Date getDate() {
        return date;
    }

    /**
     * sets the date/
     *
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * It gets the Text.
     *
     * @return the text.
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the text.
     *
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * It gets the NameReceiver.
     *
     * @return the name of the Receiver.
     */
    public String getuNameReceiver() {
        return uNameReceiver;
    }

    /**
     * Sets the name of the receiver.
     *
     * @param uNameReceiver
     */
    public void setuNameReceiver(String uNameReceiver) {
        this.uNameReceiver = uNameReceiver;
    }

    /**
     * It gets the name of the sender.
     *
     * @return the name of the sender.
     */
    public String getuNameSender() {
        return uNameSender;
    }

    /**
     * Sets the name of the sender.
     *
     * @param uNameSender
     */
    public void setuNameSender(String uNameSender) {
        this.uNameSender = uNameSender;
    }

    /**
     * String method to display the message.
     *
     * @return displays the message.
     */
    @Override
    public String toString() {
        return "Message{" + "idReceiver='" + idReceiver + '\'' + ", idSender='" + idSender + '\'' + ", uNameReceiver='" + uNameReceiver + '\'' + ", uNameSender='" + uNameSender + '\'' + ", date=" + date + ", text='" + text + '\'' + '}';
    }
}
