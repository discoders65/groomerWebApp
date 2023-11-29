package com.wjadczak.groomerWebApp.controller.mapper;

import com.wjadczak.groomerWebApp.controller.dto.AppointmentDto;
import com.wjadczak.groomerWebApp.entity.AppointmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AppointmentToAppointmentDtoMapper {

    AppointmentToAppointmentDtoMapper appointmentToAppointmentDtoMapper = Mappers.getMapper(AppointmentToAppointmentDtoMapper.class);
    AppointmentDto appointmentDto (AppointmentEntity appointment);
    List<AppointmentDto> appointmentToAppointmentDtoMapperList(List<AppointmentEntity> appointmentList);
}
