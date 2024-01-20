package com.wjadczak.groomerWebApp.service.implementation;

import com.wjadczak.groomerWebApp.dto.AppointmentDto;
import com.wjadczak.groomerWebApp.dto.AppointmentSaveRequestDto;
import com.wjadczak.groomerWebApp.dto.AppointmentSearchRequestDto;
import com.wjadczak.groomerWebApp.entity.AppointmentEntity;
import com.wjadczak.groomerWebApp.entity.UserEntity;
import com.wjadczak.groomerWebApp.errors.AppointmentNotFoundException;
import com.wjadczak.groomerWebApp.errors.ErrorMessages;
import com.wjadczak.groomerWebApp.errors.InvalidSaveAppointmentDataInputException;
import com.wjadczak.groomerWebApp.errors.InvalidSearchRequestException;
import com.wjadczak.groomerWebApp.mapper.AppointmentToAppointmentDtoMapper;
import com.wjadczak.groomerWebApp.repository.AppointmentRepository;
import com.wjadczak.groomerWebApp.repository.UserRepository;
import com.wjadczak.groomerWebApp.service.AppointmentService;
import com.wjadczak.groomerWebApp.utils.TimeParserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
@RequiredArgsConstructor
@Service
@Slf4j
public class AppointmentServiceImpl implements AppointmentService {


    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    @Override
    public List<AppointmentDto> findAppointment(AppointmentSearchRequestDto appointmentSearchRequestDto) {
        log.debug("Entering findAppointment method with search request: {}", appointmentSearchRequestDto);
        validateSearchData(appointmentSearchRequestDto);
        LocalDateTime startDateTime = TimeParserUtil.parseDateTime(appointmentSearchRequestDto.getStartDateTime());
        LocalDateTime endDateTime = TimeParserUtil.parseDateTime(appointmentSearchRequestDto.getEndDateTime());
        return findAppointmentDateBetween(startDateTime, endDateTime);
    }

    @Override
    public void saveAppointment(AppointmentSaveRequestDto appointmentSaveRequestDto) {
        validateSaveDataInput(appointmentSaveRequestDto);
        LocalDateTime startDateTime = TimeParserUtil.parseDateTime(appointmentSaveRequestDto.getStartDateTime());
        UUID currentUserId = getCurrentUserId();
        // czy ten optional jest tu konieczny? ew. czy dodać walidacje dla niepoprawnego ID, niewystępującego w bazie?
        Optional<UserEntity> currentUser = userRepository.findById(currentUserId);

        AppointmentEntity newAppointment = AppointmentEntity
                .builder()
                .dateStart(startDateTime)
                .userEntity(currentUser.get())
                .build();

        appointmentRepository.save(newAppointment);
    }

    private UUID getCurrentUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity = (UserEntity)authentication.getPrincipal();
        return userEntity.getId();
    }
    private void validateSaveDataInput(AppointmentSaveRequestDto appointmentSaveRequestDto) {
        if(nonNull(appointmentSaveRequestDto)){
            String startDateTime = appointmentSaveRequestDto.getStartDateTime();
            boolean startDateTimeIsNull = isNull(startDateTime);
            if(startDateTimeIsNull){
                throw new InvalidSaveAppointmentDataInputException(ErrorMessages.INVALID_APPOINTMENT_DATE);
            }
        } else {
            throw new InvalidSaveAppointmentDataInputException(ErrorMessages.INVALID_APPOINTMENT_DATE);
        }
    }
    private List<AppointmentDto> findAppointmentDateBetween(LocalDateTime dateStart, LocalDateTime dateEnd) {
        log.debug("Finding appointments between {} and {}", dateStart, dateEnd);
        List<AppointmentDto> appointmentsFound = AppointmentToAppointmentDtoMapper
                .appointmentToAppointmentDtoMapper
                .mapAppointmentEntitiesToDtos(appointmentRepository.findByDateStartBetween(dateStart, dateEnd));
        if(appointmentsFound.isEmpty()){
            throw new AppointmentNotFoundException(ErrorMessages.APPOINTMENT_NOT_FOUND);
        }
        log.debug("Found {} appointments between {} and {}", appointmentsFound.size(), dateStart, dateEnd);
        return appointmentsFound;
    }

    private void validateSearchData(AppointmentSearchRequestDto appointmentSearchRequestDto) {
        log.debug("Validating date-time input: {}", appointmentSearchRequestDto);
        if (nonNull(appointmentSearchRequestDto)) {
            checkSearchDataInput(appointmentSearchRequestDto);
        } else {
            log.error("Invalid search request: null input");
            throw new InvalidSearchRequestException(ErrorMessages.MISSING_SEARCH_INPUT);
        }
    }

    private void checkSearchDataInput(AppointmentSearchRequestDto appointmentSearchRequestDto){
        String startDateTime = appointmentSearchRequestDto.getStartDateTime();
        String endDateTime = appointmentSearchRequestDto.getEndDateTime();
        boolean startDateIsNullOrEndDateIsNull = isNull(startDateTime) || isNull(endDateTime);
        if(startDateIsNullOrEndDateIsNull) {
            log.error("Invalid search request: Missing startDateTime or endDateTime");
            throw new InvalidSearchRequestException(ErrorMessages.MISSING_SEARCH_INPUT);
        }
    }

}
