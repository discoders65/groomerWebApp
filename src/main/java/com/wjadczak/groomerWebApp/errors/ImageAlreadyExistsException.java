package com.wjadczak.groomerWebApp.errors;

public class ImageAlreadyExistsException extends RuntimeException{
    public ImageAlreadyExistsException(String message) {
        super(message);
    }
}
