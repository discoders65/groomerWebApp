package com.wjadczak.groomerWebApp.controller.mapper;

import com.wjadczak.groomerWebApp.controller.dto.SignUpDto;
import com.wjadczak.groomerWebApp.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SignUpDtoToUserMapper {

    SignUpDtoToUserMapper signUpDtoToUserMapper = Mappers.getMapper(SignUpDtoToUserMapper.class);

    UserEntity signUpDtoToUser(SignUpDto signUpDto);

}
