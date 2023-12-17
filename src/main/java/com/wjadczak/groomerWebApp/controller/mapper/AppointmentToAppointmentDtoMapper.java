package com.wjadczak.groomerWebApp.controller.mapper;

import com.wjadczak.groomerWebApp.controller.dto.AppointmentDto;
import com.wjadczak.groomerWebApp.entity.AppointmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AppointmentToAppointmentDtoMapper {
    AppointmentToAppointmentDtoMapper appointmentToAppointmentDtoMapper = Mappers.getMapper(AppointmentToAppointmentDtoMapper.class);
    @Mapping(target = "userId", expression = "java(appointment.getUserEntity().getId())")
    AppointmentDto mapAppointmentEntityToDto(AppointmentEntity appointment);
    List<AppointmentDto> mapAppointmentEntitiesToDtos(List<AppointmentEntity> appointmentList);
}
