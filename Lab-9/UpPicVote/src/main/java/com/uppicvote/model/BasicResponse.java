package com.uppicvote.model;

public class BasicResponse {
    private String message;
    private Integer responseCode;

    public BasicResponse() {
        this.message = "Default response message";
        this.responseCode = 0;
    }

    public BasicResponse(String message, Integer responseCode) {
        this.message = message;
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

}
