package com.smartMatch.user;
/**
 * This is created for returning the json file to the frontend.
 * @author Rishabh bansal
 */
public class Message {
    private String message;
    public Message(String s){
        message = s;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
