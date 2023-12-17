package com.wjadczak.groomerWebApp.service.implementation;

import com.wjadczak.groomerWebApp.controller.dto.AppointmentDto;
import com.wjadczak.groomerWebApp.controller.mapper.AppointmentToAppointmentDtoMapper;
import com.wjadczak.groomerWebApp.errors.InvalidSearchRequestException;
import com.wjadczak.groomerWebApp.repository.AppointmentRepository;
import com.wjadczak.groomerWebApp.request.AppointmentSearchRequest;
import com.wjadczak.groomerWebApp.service.AppointmentService;
import com.wjadczak.groomerWebApp.utils.TimeParserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
@RequiredArgsConstructor
@Service
public class AppointmentServiceImpl implements AppointmentService {


    private final AppointmentRepository appointmentRepository;

    @Override
    public List<AppointmentDto> findAppointment(AppointmentSearchRequest appointmentSearchRequest) {
        validateDateTimeInput(appointmentSearchRequest);
        LocalDateTime startDateTime = TimeParserUtil.parseDateTime(appointmentSearchRequest.getStartDateTime());
        LocalDateTime endDateTime = TimeParserUtil.parseDateTime(appointmentSearchRequest.getEndDateTime());
        return findAppointmentDateBetween(startDateTime, endDateTime);
    }

    private List<AppointmentDto> findAppointmentDateBetween(LocalDateTime dateStart, LocalDateTime dateEnd) {
        return AppointmentToAppointmentDtoMapper
                .appointmentToAppointmentDtoMapper
                .mapAppointmentEntitiesToDtos(appointmentRepository.findByDateStartBetween(dateStart, dateEnd));
    }

    private void validateDateTimeInput(AppointmentSearchRequest appointmentSearchRequest) {
         if(nonNull(appointmentSearchRequest)) {
            String startDateTime = appointmentSearchRequest.getStartDateTime();
            String endDateTime = appointmentSearchRequest.getEndDateTime();
            boolean startDateIsNullOrEndDateIsNull = isNull(startDateTime) || isNull(endDateTime);
            if(startDateIsNullOrEndDateIsNull) {
                throw new InvalidSearchRequestException("Must provide a start and/or end date to retrieve calendar appointments.");
            }
        } else {
            throw new InvalidSearchRequestException("Search parameters startDateTime and endDateTime must be provided.");
        }
    }

}
