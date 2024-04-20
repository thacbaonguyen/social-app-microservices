package com.thacbao.social.postservice.exception;

import com.thacbao.social.postservice.dto.response.ExceptionResponse;
import com.thacbao.social.postservice.enums.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<?> handlingDataNotFoundException(DataNotFoundException e){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setCode(ErrorCode.NO_CONTENT.getCode());
        exceptionResponse.setMessage(e.getMessage());
        exceptionResponse.setStatus(ErrorCode.NO_CONTENT.getStatusCode());
        exceptionResponse.setTimestamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        exceptionResponse.setError("Not found");
        return ResponseEntity.status(ErrorCode.NO_CONTENT.getStatusCode()).body(exceptionResponse);
    }
    @ExceptionHandler(PermissionException.class)
    public ResponseEntity<?> handlingPermissionException(PermissionException e){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setCode(ErrorCode.PERMISSION.getCode());
        exceptionResponse.setMessage(e.getMessage());
        exceptionResponse.setStatus(ErrorCode.PERMISSION.getStatusCode());
        exceptionResponse.setTimestamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        exceptionResponse.setError("Not found");
        return ResponseEntity.status(ErrorCode.PERMISSION.getStatusCode()).body(exceptionResponse);
    }
}
