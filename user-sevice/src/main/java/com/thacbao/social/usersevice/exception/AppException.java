package com.thacbao.social.usersevice.exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class AppException extends RuntimeException{
    public AppException (ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
    private ErrorCode errorCode;
}
