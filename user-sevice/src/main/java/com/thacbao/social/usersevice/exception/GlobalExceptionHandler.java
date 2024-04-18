package com.thacbao.social.usersevice.exception;

import com.thacbao.social.usersevice.dto.response.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handlingRuntimeException(RuntimeException e){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setCode(ErrorCode.BAD_REQUEST.getCode());
        exceptionResponse.setMessage(e.getMessage());
        exceptionResponse.setStatus(ErrorCode.BAD_REQUEST.getStatusCode());
        exceptionResponse.setTimestamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        exceptionResponse.setError("Runtime");
        return ResponseEntity.status(ErrorCode.BAD_REQUEST.getStatusCode()).body(exceptionResponse);
    }
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
    @ExceptionHandler(AlreadyExistingException.class)
    public ResponseEntity<?> handlingAlreadyExistingException(AlreadyExistingException e){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setCode(ErrorCode.ALREADY.getCode());
        exceptionResponse.setMessage(e.getMessage());
        exceptionResponse.setStatus(ErrorCode.ALREADY.getStatusCode());
        exceptionResponse.setTimestamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        exceptionResponse.setError("Already");
        return ResponseEntity.status(ErrorCode.ALREADY.getStatusCode()).body(exceptionResponse);
    }
    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<?> handlingInvalidPasswordException(InvalidPasswordException e){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setCode(ErrorCode.PASSWORD_ERROR.getCode());
        exceptionResponse.setMessage(e.getMessage());
        exceptionResponse.setStatus(ErrorCode.PASSWORD_ERROR.getStatusCode());
        exceptionResponse.setTimestamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        exceptionResponse.setError("Already");
        return ResponseEntity.status(ErrorCode.PASSWORD_ERROR.getStatusCode()).body(exceptionResponse);
    }
    @ExceptionHandler(InvalidAccountException.class)
    public ResponseEntity<?> handlingInvalidAccountException(InvalidAccountException e){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setCode(ErrorCode.ACCOUNT_ERROR.getCode());
        exceptionResponse.setMessage(e.getMessage());
        exceptionResponse.setStatus(ErrorCode.ACCOUNT_ERROR.getStatusCode());
        exceptionResponse.setTimestamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        exceptionResponse.setError("Already");
        return ResponseEntity.status(ErrorCode.ACCOUNT_ERROR.getStatusCode()).body(exceptionResponse);
    }
}
