package com.wjadczak.groomerWebApp.errors;

public class InvalidSearchRequestException extends RuntimeException{

    public InvalidSearchRequestException(String message){
        super(message);
    }
}
