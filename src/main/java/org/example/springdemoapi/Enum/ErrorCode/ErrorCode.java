package org.example.springdemoapi.Enum.ErrorCode;

public enum ErrorCode {
    USER_EXISTED(1001,"User Existed"),
    USERNAME_INVALID(1002, "Username must be at least 3 characters"),
    USERPASSWORD_INVALID(1003, "UserPassword must be at least 3 characters"),
    USER_NOT_EXISTED (1004, "User Not Existed"),
    UNAUTHENTICAED(1005, "Unauthenticated"),
    ;

    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
