package com.wjadczak.groomerWebApp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String userName;
    private String email;
    private String password;

}
