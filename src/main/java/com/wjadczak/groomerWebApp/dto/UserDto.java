package com.wjadczak.groomerWebApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {

    private UUID id;
    private String name;
    private String userName;
    private String email;
    private int mobile;

}