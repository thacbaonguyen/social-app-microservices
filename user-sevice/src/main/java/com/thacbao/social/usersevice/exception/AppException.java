package com.thacbao.social.usersevice.exception;

import com.thacbao.social.usersevice.enums.ErrorCode;
import lombok.Data;

@Data
public class AppException extends RuntimeException{
    public AppException (ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
    private ErrorCode errorCode;
}
