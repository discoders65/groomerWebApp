package com.wjadczak.groomerWebApp.controller.dto;

import com.wjadczak.groomerWebApp.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String userName;
    private String email;
    private Role role;

}