package com.wjadczak.groomerWebApp.service;

import com.wjadczak.groomerWebApp.controller.dto.SignUpDto;
import com.wjadczak.groomerWebApp.controller.dto.UserDto;
import com.wjadczak.groomerWebApp.entity.UserEntity;

import java.util.List;

public interface UserService {

    UserDto createNewUser(SignUpDto signUpDto);
    List<UserEntity> getAllUsers();
    UserEntity findUserByEmail(String email);
    UserEntity findUserByUserName(String userName);
}
