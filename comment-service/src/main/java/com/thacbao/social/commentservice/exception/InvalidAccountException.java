package com.thacbao.social.commentservice.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class InvalidAccountException extends RuntimeException{
    public InvalidAccountException (String message){
        super(message);
    }
}
