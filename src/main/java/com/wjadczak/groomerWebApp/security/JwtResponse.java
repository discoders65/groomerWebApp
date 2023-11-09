package com.wjadczak.groomerWebApp.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -27312747892398L;
    private final String jwtToken;



}
