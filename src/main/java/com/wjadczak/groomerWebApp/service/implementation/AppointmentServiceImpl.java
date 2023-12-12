package com.wjadczak.groomerWebApp.service.implementation;

import com.wjadczak.groomerWebApp.controller.dto.AppointmentDto;
import com.wjadczak.groomerWebApp.controller.mapper.AppointmentToAppointmentDtoMapper;
import com.wjadczak.groomerWebApp.errors.InvalidRequestException;
import com.wjadczak.groomerWebApp.repository.AppointmentRepository;
import com.wjadczak.groomerWebApp.request.SearchRequest;
import com.wjadczak.groomerWebApp.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired // -->
    AppointmentRepository appointmentRepository;

    @Override
    public List<AppointmentDto> findAppointmentDateBetween(LocalDateTime dateStart, LocalDateTime dateEnd) {
        return AppointmentToAppointmentDtoMapper.appointmentToAppointmentDtoMapper.appointmentToAppointmentDtoMapperList(appointmentRepository.findByDateStartBetween(dateStart, dateEnd));
    }

    @Override
    public void validateDateTimeInput(SearchRequest searchRequest) {

        if(searchRequest != null)
        {
            String startDateTime = searchRequest.getStartDateTime();
            String endDateTime = searchRequest.getEndDateTime();

            if(startDateTime == null || endDateTime == null)
            {
                throw new InvalidRequestException("Must provide a start and/or end date to retrieve calendar appointments.");
            }

        }
        else
        {
            throw new InvalidRequestException("Search parameters startDateTime and endDateTime must be provided.");
        }

    }
}
