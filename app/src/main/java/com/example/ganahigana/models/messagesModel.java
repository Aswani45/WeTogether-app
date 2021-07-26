package com.example.ganahigana.models;

public class messagesModel {
    String uID,message;
    Long timestamp;

    public messagesModel(String uID, String message, Long timestamp) {
        this.uID = uID;
        this.message = message;
        this.timestamp = timestamp;
    }

    public messagesModel(String uID, String message) {
        this.uID = uID;
        this.message = message;
    }
    public messagesModel(){}

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
