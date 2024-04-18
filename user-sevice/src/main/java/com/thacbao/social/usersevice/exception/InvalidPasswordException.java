package com.thacbao.social.usersevice.exception;

public class InvalidPasswordException extends RuntimeException{
    public InvalidPasswordException (String message){
        super(message);
    }
}
