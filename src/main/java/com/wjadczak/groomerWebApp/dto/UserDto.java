package com.wjadczak.groomerWebApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class UserDto {

    private UUID id;
    private String name;
    private String userName;
    private String email;
    private int mobile;

}