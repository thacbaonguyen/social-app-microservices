package com.thacbao.social.usersevice.exception;

public class InvalidAccountException extends RuntimeException{
    public InvalidAccountException(String message){
        super(message);
    }
}
