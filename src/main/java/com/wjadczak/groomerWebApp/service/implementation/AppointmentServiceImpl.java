package com.wjadczak.groomerWebApp.service.implementation;

import com.wjadczak.groomerWebApp.errors.ErrorMessages;
import com.wjadczak.groomerWebApp.dto.AppointmentDto;
import com.wjadczak.groomerWebApp.mapper.AppointmentToAppointmentDtoMapper;
import com.wjadczak.groomerWebApp.errors.InvalidSearchRequestException;
import com.wjadczak.groomerWebApp.repository.AppointmentRepository;
import com.wjadczak.groomerWebApp.dto.AppointmentSearchRequestDto;
import com.wjadczak.groomerWebApp.service.AppointmentService;
import com.wjadczak.groomerWebApp.utils.TimeParserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
@RequiredArgsConstructor
@Service
@Slf4j
public class AppointmentServiceImpl implements AppointmentService {


    private final AppointmentRepository appointmentRepository;

    @Override
    public List<AppointmentDto> findAppointment(AppointmentSearchRequestDto appointmentSearchRequestDto) {
        log.debug("Entering findAppointment method with search request: {}", appointmentSearchRequestDto);
        validateDateTimeInput(appointmentSearchRequestDto);
        LocalDateTime startDateTime = TimeParserUtil.parseDateTime(appointmentSearchRequestDto.getStartDateTime());
        LocalDateTime endDateTime = TimeParserUtil.parseDateTime(appointmentSearchRequestDto.getEndDateTime());
        return findAppointmentDateBetween(startDateTime, endDateTime);
    }

    private List<AppointmentDto> findAppointmentDateBetween(LocalDateTime dateStart, LocalDateTime dateEnd) {
        log.debug("Finding appointments between {} and {}", dateStart, dateEnd);
        List<AppointmentDto> appointmentsFound = AppointmentToAppointmentDtoMapper
                .appointmentToAppointmentDtoMapper
                .mapAppointmentEntitiesToDtos(appointmentRepository.findByDateStartBetween(dateStart, dateEnd));
        log.debug("Found {} appointments between {} and {}", appointmentsFound.size(), dateStart, dateEnd);
        return appointmentsFound;
    }

    private void validateDateTimeInput(AppointmentSearchRequestDto appointmentSearchRequestDto) {
        log.debug("Validating date-time input: {}", appointmentSearchRequestDto);
        if (nonNull(appointmentSearchRequestDto)) {
            checkDateTimeInput(appointmentSearchRequestDto);
        } else {
            log.error("Invalid search request: null input");
            throw new InvalidSearchRequestException(ErrorMessages.MISSING_SEARCH_INPUT);
        }
    }

    private void checkDateTimeInput(AppointmentSearchRequestDto appointmentSearchRequestDto){
        String startDateTime = appointmentSearchRequestDto.getStartDateTime();
        String endDateTime = appointmentSearchRequestDto.getEndDateTime();
        boolean startDateIsNullOrEndDateIsNull = isNull(startDateTime) || isNull(endDateTime);
        if(startDateIsNullOrEndDateIsNull) {
            log.error("Invalid search request: Missing startDateTime or endDateTime");
            throw new InvalidSearchRequestException(ErrorMessages.MISSING_SEARCH_INPUT);
        }
    }

}
