package com.thacbao.social.shareservice.exception;

import com.thacbao.social.shareservice.dto.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handlingDataNotFoundException(DataNotFoundException e){
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .code(1003)
                .message(e.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .error("Not found")
                .timestamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }
    @ExceptionHandler(PermissionException.class)
    public ResponseEntity<ExceptionResponse> handlingPermissionException(PermissionException e){
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .code(1007)
                .message(e.getMessage())
                .status(HttpStatus.FORBIDDEN)
                .error("Permission account")
                .timestamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .build();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exceptionResponse);
    }
}
