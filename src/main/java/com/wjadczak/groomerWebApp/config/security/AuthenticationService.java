package com.wjadczak.groomerWebApp.config.security;

import com.wjadczak.groomerWebApp.dto.SignUpDto;
import com.wjadczak.groomerWebApp.dto.SignInDto;
import com.wjadczak.groomerWebApp.dto.JwtAuthenticationResponseDto;


public interface AuthenticationService {

    void signUp(SignUpDto signUpDto);
    JwtAuthenticationResponseDto signIn(SignInDto signInDto);
}
