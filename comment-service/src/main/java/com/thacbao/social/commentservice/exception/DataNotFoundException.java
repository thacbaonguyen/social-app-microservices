package com.thacbao.social.commentservice.exception;

public class DataNotFoundException extends RuntimeException{
    public DataNotFoundException (String message){
        super(message);
    }
}
