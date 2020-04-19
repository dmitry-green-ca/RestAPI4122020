package com.example.rest_api.controller.shared;

public class ErrorMessage {
    private String timestamp;
    private String message;

    public String getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    //{"timestamp":1587276951306,"message":"Player with id: [23] not found"}
}
