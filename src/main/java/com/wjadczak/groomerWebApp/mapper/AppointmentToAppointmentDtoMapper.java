package com.wjadczak.groomerWebApp.mapper;

import com.wjadczak.groomerWebApp.dto.AppointmentDto;
import com.wjadczak.groomerWebApp.entity.AppointmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AppointmentToAppointmentDtoMapper {
    AppointmentToAppointmentDtoMapper appointmentToAppointmentDtoMapper = Mappers.getMapper(AppointmentToAppointmentDtoMapper.class);

    @Mapping(source = "userEntity.id", target = "userId")
    AppointmentDto mapAppointmentToDto(AppointmentEntity appointment);

    List<AppointmentDto> mapAppointmentsToDtos(List<AppointmentEntity> appointmentList);
}
