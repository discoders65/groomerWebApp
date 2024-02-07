package com.wjadczak.groomerWebApp.service;

import com.wjadczak.groomerWebApp.dto.ImageDto;
import com.wjadczak.groomerWebApp.dto.UserDto;

import java.util.List;
import java.util.UUID;

public interface AdminService {

    void confirmAppointment(UUID id);
    ImageDto downloadImage(UUID id);
    List<UserDto> getAllUsers();
    UserDto getUserById(UUID id);
    void  cancelAppointment(UUID id);
}
