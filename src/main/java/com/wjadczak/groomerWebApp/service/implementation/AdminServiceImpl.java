package com.wjadczak.groomerWebApp.service.implementation;

import com.wjadczak.groomerWebApp.dto.ImageDto;
import com.wjadczak.groomerWebApp.dto.UserDto;
import com.wjadczak.groomerWebApp.entity.AppointmentEntity;
import com.wjadczak.groomerWebApp.entity.UserEntity;
import com.wjadczak.groomerWebApp.errors.AppointmentNotFoundException;
import com.wjadczak.groomerWebApp.errors.EntityNotFoundException;
import com.wjadczak.groomerWebApp.errors.ErrorMessages;
import com.wjadczak.groomerWebApp.errors.InvalidSearchRequestException;
import com.wjadczak.groomerWebApp.mapper.ImageToImageDtoMapper;
import com.wjadczak.groomerWebApp.mapper.UserToUserDtoMapper;
import com.wjadczak.groomerWebApp.repository.AppointmentRepository;
import com.wjadczak.groomerWebApp.repository.ImageRepository;
import com.wjadczak.groomerWebApp.repository.UserRepository;
import com.wjadczak.groomerWebApp.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final ImageRepository imageRepository;
    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    @Override
    public void confirmAppointment(UUID id) {
        AppointmentEntity appointment = appointmentRepository
                .findById(id)
                .orElseThrow(()  -> new AppointmentNotFoundException(ErrorMessages.APPOINTMENT_NOT_FOUND));
        appointment.setAccepted(true);
        appointmentRepository.save(appointment);
    }

    @Override
    public ImageDto downloadImage(UUID id) {
        ImageDto image = ImageToImageDtoMapper.imageToImageDtoMapper
                .mapImagetoImageDto(imageRepository
                        .findById(id)
                        .orElseThrow(() -> new EntityNotFoundException(ErrorMessages.INVALID_ID)));
        return image;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserDto> users = UserToUserDtoMapper.userToUserDtoMapper.mapUsersToDtos(userRepository.findAll());
        return users;
    }

    @Override
    public UserDto getUserById(UUID id) {
        UserEntity user = userRepository
                .findById(id)
                .orElseThrow(() -> new InvalidSearchRequestException(ErrorMessages.INVALID_ID));
        UserDto userDto = UserToUserDtoMapper.userToUserDtoMapper.mapUserToDto(user);
        return userDto;
    }
}
