package com.wjadczak.groomerWebApp.config.validation;

import com.wjadczak.groomerWebApp.errors.ErrorMessages;
import com.wjadczak.groomerWebApp.errors.PasswordValidationException;

public class PasswordSpecialCharsValidator implements Validator{
    private static final String SPECIAL_CHARS = "!@#$%^&*()_+=-?|:;'.,/<>][{}";
    @Override
    public void validate(String valueToValidate) {
        boolean noSpecialCharFound = valueToValidate
            .chars()
            .mapToObj(c -> (char) c)
            .noneMatch(ch -> SPECIAL_CHARS.indexOf(ch) != -1);
        if(noSpecialCharFound){
            throw new PasswordValidationException(ErrorMessages.NO_SPECIAL_CHARS_FOUND + SPECIAL_CHARS);
        }
    }
}
