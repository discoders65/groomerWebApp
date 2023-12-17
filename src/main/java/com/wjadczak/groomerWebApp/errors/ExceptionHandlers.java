package com.wjadczak.groomerWebApp.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(InvalidSearchRequestException.class)
    public ResponseEntity<String> handleInvalidSearchRequestException(InvalidSearchRequestException invalidSearchRequestException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(invalidSearchRequestException.getMessage());
    }
    @ExceptionHandler(InvalidUserDataInputException.class)
    public ResponseEntity<String> handleInvalidUserDataInputException(InvalidUserDataInputException invalidUserDataInputException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(invalidUserDataInputException.getMessage());
    }
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException userAlreadyExistsException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userAlreadyExistsException.getMessage());
    }
}

