package com.wjadczak.groomerWebApp.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JwtRequest implements Serializable {

    private static final long serialVersionUID = 3298743927620365L;
    private String username;
    private String password;

}
