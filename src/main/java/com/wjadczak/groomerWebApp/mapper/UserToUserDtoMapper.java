package com.wjadczak.groomerWebApp.mapper;

import com.wjadczak.groomerWebApp.dto.UserDto;
import com.wjadczak.groomerWebApp.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserToUserDtoMapper {
    UserToUserDtoMapper userToUserDtoMapper = Mappers.getMapper(UserToUserDtoMapper.class);

    UserDto mapUserToDto(UserEntity userEntity);
    List<UserDto> mapUsersToDtos(List<UserEntity> userList);
}
