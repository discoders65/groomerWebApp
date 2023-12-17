package com.wjadczak.groomerWebApp.errors;

public class InvalidUserDataInputException extends RuntimeException{
    public InvalidUserDataInputException(String message){
        super(message);
    }
}
