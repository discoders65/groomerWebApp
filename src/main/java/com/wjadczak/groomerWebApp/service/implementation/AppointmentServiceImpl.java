package com.wjadczak.groomerWebApp.service.implementation;

import com.wjadczak.groomerWebApp.dto.AppointmentDto;
import com.wjadczak.groomerWebApp.dto.AppointmentSaveRequestDto;
import com.wjadczak.groomerWebApp.dto.AppointmentSearchRequestDto;
import com.wjadczak.groomerWebApp.entity.AppointmentEntity;
import com.wjadczak.groomerWebApp.entity.UserEntity;
import com.wjadczak.groomerWebApp.errors.AppointmentNotFoundException;
import com.wjadczak.groomerWebApp.errors.ErrorMessages;
import com.wjadczak.groomerWebApp.mapper.AppointmentToAppointmentDtoMapper;
import com.wjadczak.groomerWebApp.repository.AppointmentRepository;
import com.wjadczak.groomerWebApp.repository.UserRepository;
import com.wjadczak.groomerWebApp.service.AppointmentService;
import com.wjadczak.groomerWebApp.service.validators.AppointmentServiceValidator;
import com.wjadczak.groomerWebApp.utils.TimeParserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final AppointmentServiceValidator appointmentServiceValidator;

    @Override
    public List<AppointmentDto> findAppointment(AppointmentSearchRequestDto appointmentSearchRequestDto) {
        log.debug("Entering findAppointment method with search request: {}", appointmentSearchRequestDto);
        appointmentServiceValidator.validateAppointmentSearchData(appointmentSearchRequestDto);
        LocalDateTime startDateTime = TimeParserUtil.parseDateTime(appointmentSearchRequestDto.getStartDateTime());
        LocalDateTime endDateTime = TimeParserUtil.parseDateTime(appointmentSearchRequestDto.getEndDateTime());
        return findAppointmentDateBetween(startDateTime, endDateTime);
    }

    @Override
    public void saveAppointment(AppointmentSaveRequestDto appointmentSaveRequestDto) {
        appointmentServiceValidator.validateSaveAppointmentDataInput(appointmentSaveRequestDto);
        LocalDateTime startDateTime = TimeParserUtil.parseDateTime(appointmentSaveRequestDto.getStartDateTime());
        UUID currentUserId = getCurrentUserId();
        UserEntity currentUser = userRepository
                .findById(currentUserId)
                .orElseThrow(() -> new UsernameNotFoundException(ErrorMessages.INVALID_USER_ID));

        AppointmentEntity newAppointment = AppointmentEntity
                .builder()
                .dateStart(startDateTime)
                .userEntity(currentUser)
                .build();

        appointmentRepository.save(newAppointment);
    }

    private UUID getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity = (UserEntity) authentication.getPrincipal();
        return userEntity.getId();
    }

    private List<AppointmentDto> findAppointmentDateBetween(LocalDateTime dateStart, LocalDateTime dateEnd) {
        log.debug("Finding appointments between {} and {}", dateStart, dateEnd);
        List<AppointmentDto> appointmentsFound = AppointmentToAppointmentDtoMapper
                .appointmentToAppointmentDtoMapper
                .mapAppointmentEntitiesToDtos(appointmentRepository.findByDateStartBetween(dateStart, dateEnd));
        if (appointmentsFound.isEmpty()) {
            throw new AppointmentNotFoundException(ErrorMessages.APPOINTMENT_NOT_FOUND);
        }
        log.debug("Found {} appointments between {} and {}", appointmentsFound.size(), dateStart, dateEnd);
        return appointmentsFound;
    }

}
