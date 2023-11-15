package com.wjadczak.groomerWebApp.security;

import com.wjadczak.groomerWebApp.dao.request.SignInRequest;
import com.wjadczak.groomerWebApp.dao.request.SignUpRequest;
import com.wjadczak.groomerWebApp.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {

    JwtAuthenticationResponse signUp(SignUpRequest request);
    JwtAuthenticationResponse signIn(SignInRequest request);
}
