package com.wjadczak.groomerWebApp.errors;

public class ErrorMessages {

    // User-related errors:
    public static final String INVALID_USER_OR_EMAIL = "Invalid email or password.";
    public static final String REGISTRATION_FIELDS_MUST_BE_FILLED_OUT = "All fields in the user registration must be filled out.";
    public static final String USER_ALREADY_EXISTS = "User registered with this mail and/or username already exists.";
    public static final String EMPTY_REGISTRATION_FORM = "User registration form can not be empty.";

    // Password validation errors:
    public static final String PASSWORD_TOO_SHORT = "Password is too short.";
    public static final String NO_SPECIAL_CHARS_FOUND = "Password must contain at least one of the characters: .";
    // Calendar-related errors:
    public static final String MISSING_SEARCH_INPUT = "Must provide a start and/or end date to retrieve calendar appointments.";
    private ErrorMessages(){

    }
}
