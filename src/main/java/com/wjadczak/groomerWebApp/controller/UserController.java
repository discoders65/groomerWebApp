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
    public ResponseEntity<SignUpDto> signup(@RequestBody SignUpDto signUpDto){ // TODO SignUpReqDto
        userService.saveUser(signUpDto); // TODO saveUser => createNewUser(), userService.saveUser(UserDto)
        // TODO signUpDto - wejsciowe
        // signUpDto map to UserDto (MapStruct tool)
        // UserDto for modification
        //
        return ResponseEntity.ok(signUpDto); //TODO


        //TODO
/*
        in: SignUpDto
        UserDto
        User
        out:UserDto or
        out: UserDto -> UserCreatedDtoRes

 */
    }
    @GetMapping("/users")
    public List<UserDto> getAllUsers(){
        return mapUserToUserDto(userService.getAllUsers());
    }
}
