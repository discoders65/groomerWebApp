package com.wjadczak.groomerWebApp.controller.mapper;

import com.wjadczak.groomerWebApp.controller.dto.UserDto;
import com.wjadczak.groomerWebApp.entity.UserEntity;
import org.mapstruct.factory.Mappers;

import java.util.List;

public interface UserToUserDtoMapper {

    UserToUserDtoMapper userToUserDtoMapper = Mappers.getMapper(UserToUserDtoMapper.class);
    UserDto userToUserDto(UserEntity user);
    List<UserDto> userListToUserDtoList(List<UserEntity> userList);
}
