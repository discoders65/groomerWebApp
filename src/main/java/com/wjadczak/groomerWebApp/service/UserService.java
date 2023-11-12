package com.wjadczak.groomerWebApp.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

    UserDetailsService userDetailsService();

//
//    UserDto createNewUser(SignUpDto signUpDto);
//    List<UserEntity> getAllUsers();
//    UserEntity findUserByEmail(String email);
//    UserEntity findUserByUserName(String userName);
}
