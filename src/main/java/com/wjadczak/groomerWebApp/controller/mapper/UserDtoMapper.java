package com.wjadczak.groomerWebApp.controller.mapper;

import com.wjadczak.groomerWebApp.controller.dto.UserDto;
import com.wjadczak.groomerWebApp.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserDtoMapper {

    public static List<UserDto> mapUserToUserDto(List<User> users){
        return users.stream()
                .map(user -> new UserDto(user.getId(), user.getName(), user.getUserName(), user.getEmail(), user.getRoles()))
                .collect(Collectors.toList());
    }

}
