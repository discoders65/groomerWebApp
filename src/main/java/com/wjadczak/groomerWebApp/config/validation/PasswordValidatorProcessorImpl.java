package com.wjadczak.groomerWebApp.config.validation;

import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class PasswordValidatorProcessorImpl implements PasswordValidatorProcessor{

    private static List<Validator> passwordValidators = List.of(
            new PasswordLengthValidator(),
                new PasswordSpecialCharsValidator()
        );

    public void validate(String password){
        passwordValidators.forEach(passwordValidator -> {
            passwordValidator.validate(password);
        });
    }
}
