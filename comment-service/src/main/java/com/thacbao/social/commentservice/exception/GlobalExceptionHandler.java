package com.thacbao.social.commentservice.exception;

import com.thacbao.social.commentservice.dto.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
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
        exceptionResponse.setCode(1003);
        exceptionResponse.setStatus(HttpStatus.BAD_REQUEST);
        exceptionResponse.setMessage(e.getMessage());
        exceptionResponse.setTimestamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        exceptionResponse.setError("404 not found");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }
    @ExceptionHandler(InvalidAccountException.class)
    public ResponseEntity<?> handlingInvalidAccountException(InvalidAccountException e){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setCode(1006);
        exceptionResponse.setStatus(HttpStatus.FORBIDDEN);
        exceptionResponse.setMessage(e.getMessage());
        exceptionResponse.setTimestamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        exceptionResponse.setError("Account error");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exceptionResponse);
    }
}
