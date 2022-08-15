package com.example.OnlineCourses.exceptions;

import lombok.Data;

@Data
public class RestAPIResponse {

    private int status;
    private String message;
    private Object data;

    public RestAPIResponse(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public RestAPIResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
