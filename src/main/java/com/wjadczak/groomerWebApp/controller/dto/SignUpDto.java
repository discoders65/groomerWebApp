package com.wjadczak.groomerWebApp.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpDto {
    private Long id;
    private String name;
    private String userName;
    private String email;
    private String password;
}
