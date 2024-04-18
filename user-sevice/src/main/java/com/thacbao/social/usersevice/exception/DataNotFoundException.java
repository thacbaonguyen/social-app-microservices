package com.thacbao.social.usersevice.exception;

import lombok.Data;

@Data
public class DataNotFoundException extends RuntimeException{
    public DataNotFoundException(String message){
        super(message);
    }
}
