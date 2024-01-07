package com.wjadczak.groomerWebApp.config.validation;

import com.wjadczak.groomerWebApp.errors.ErrorMessages;
import com.wjadczak.groomerWebApp.errors.PasswordValidationException;

public class PasswordLengthValidator implements Validator{
    private static final Integer MIN_PASSWORD_LENGTH = 8;
    @Override
    public void validate(String valueToValidate) {
        final int passwordLength = valueToValidate.length();
        boolean passwordLengthNotValid = passwordLength < 8;
        if(passwordLengthNotValid){
            throw new PasswordValidationException(ErrorMessages.PASSWORD_TOO_SHORT);
        }
    }
}
