package com.wjadczak.groomerWebApp.controller;

import com.wjadczak.groomerWebApp.controller.dto.SignUpDto;
import com.wjadczak.groomerWebApp.controller.dto.UserDto;
import com.wjadczak.groomerWebApp.controller.mapper.UserToUserDtoMapper;
import com.wjadczak.groomerWebApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpDto signUpDto){
        return ResponseEntity.ok(userService.createNewUser(signUpDto));
    }
    @GetMapping("/users")
    public List<UserDto> getAllUsers(){
        return UserToUserDtoMapper.userToUserDtoMapper.userListToUserDtoList(userService.getAllUsers());
    }
}
