package com.thacbao.social.usersevice.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    BAD_REQUEST(1002, "Execution cannot be performed", HttpStatus.BAD_REQUEST),
    NO_CONTENT(1003, "Not found",HttpStatus.BAD_REQUEST),
    ALREADY(1004, "Already exist", HttpStatus.BAD_REQUEST),
    PASSWORD_ERROR(1005, "Password", HttpStatus.BAD_REQUEST),
    ACCOUNT_ERROR(1006, "Account", HttpStatus.BAD_REQUEST)
    ;
    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private int code;
    private String message;
    private HttpStatusCode statusCode;
}
