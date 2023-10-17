package com.wjadczak.groomerWebApp.controller;

import com.wjadczak.groomerWebApp.controller.dto.SignUpDto;
import com.wjadczak.groomerWebApp.controller.dto.UserDto;
import com.wjadczak.groomerWebApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.wjadczak.groomerWebApp.controller.mapper.UserDtoMapper.mapUserToUserDto;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<SignUpDto> signup(@RequestBody SignUpDto signUpDto){
        userService.saveUser(signUpDto);
        return ResponseEntity.ok(signUpDto);
    }
    @GetMapping("/users")
    public List<UserDto> getAllUsers(){
        return mapUserToUserDto(userService.getAllUsers());
    }
}
