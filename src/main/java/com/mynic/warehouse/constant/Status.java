package com.mynic.warehouse.constant;

public enum Status {
    SUCCESS(               1,  "Successful"),
    INTERNAL_SERVER_ERROR( 2,  "Internal server error"),
    USER_NOT_FOUND(        3,  "User not found!");
    public int status;
    public String message;
    Status (int status, String message) {
        this.status = status;
        this.message = message;
    }
}
