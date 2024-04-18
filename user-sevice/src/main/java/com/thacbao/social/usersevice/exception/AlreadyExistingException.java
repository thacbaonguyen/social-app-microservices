package com.thacbao.social.usersevice.exception;

public class AlreadyExistingException extends RuntimeException{
    public AlreadyExistingException(String message){
        super(message);
    }
}
