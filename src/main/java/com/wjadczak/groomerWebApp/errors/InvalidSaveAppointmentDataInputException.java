package com.wjadczak.groomerWebApp.errors;

public class InvalidSaveAppointmentDataInputException extends RuntimeException{
    public InvalidSaveAppointmentDataInputException(String message){
        super(message);
    }
}
