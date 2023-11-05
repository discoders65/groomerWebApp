package com.wjadczak.groomerWebApp.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SignUpDto {
    private String name;
    private String userName;
    private String email;
    private String password;
}
