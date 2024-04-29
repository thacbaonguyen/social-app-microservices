package com.thacbao.social.postservice.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    NOT_FOUND(1003, "Not found",HttpStatus.NOT_FOUND),
    PERMISSION(1007, "Permission", HttpStatus.UNAUTHORIZED)
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

