package com.example.HotelManagement.DTO;

public class MessageResponse {

    private String message;
    private MessageType messageType;

    public MessageResponse() {}

    public MessageResponse(String message, MessageType messageType) {
        this.message = message;
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }
}
