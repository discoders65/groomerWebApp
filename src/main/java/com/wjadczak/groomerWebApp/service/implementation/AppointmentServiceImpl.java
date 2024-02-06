package com.wjadczak.groomerWebApp.service.implementation;

import com.wjadczak.groomerWebApp.config.security.AuthenticationHelper;
import com.wjadczak.groomerWebApp.dto.AppointmentDto;
import com.wjadczak.groomerWebApp.dto.AppointmentSaveRequestDto;
import com.wjadczak.groomerWebApp.dto.AppointmentSearchRequestDto;
import com.wjadczak.groomerWebApp.dto.CancelAppointmentDto;
import com.wjadczak.groomerWebApp.entity.AppointmentEntity;
import com.wjadczak.groomerWebApp.errors.AppointmentNotFoundException;
import com.wjadczak.groomerWebApp.errors.ErrorMessages;
import com.wjadczak.groomerWebApp.mapper.AppointmentToAppointmentDtoMapper;
import com.wjadczak.groomerWebApp.repository.AppointmentRepository;
import com.wjadczak.groomerWebApp.service.AppointmentService;
import com.wjadczak.groomerWebApp.service.validators.AppointmentServiceValidator;
import com.wjadczak.groomerWebApp.utils.TimeParserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentServiceValidator appointmentServiceValidator;
    private final AuthenticationHelper authenticationHelper;

    @Override
    public void cancelCurrentUserAppointment(CancelAppointmentDto cancelAppointmentDto) {
        appointmentServiceValidator.validateCancelAppointmentDto(cancelAppointmentDto);
        AppointmentEntity appointmentEntity = appointmentRepository
                .findById(cancelAppointmentDto.getAppointmentId())
                .orElseThrow(() -> new AppointmentNotFoundException(ErrorMessages.INVALID_APPOINTMENT_ID));
        appointmentEntity.setCancelled(true);
        appointmentRepository.save(appointmentEntity);
    }

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

        AppointmentEntity newAppointment = AppointmentEntity
                .builder()
                .dateStart(startDateTime)
                .userEntity(authenticationHelper.getCurrentUser())
                .accepted(false)
                .cancelled(false)
                .build();

        appointmentRepository.save(newAppointment);
    }

    private List<AppointmentDto> findAppointmentDateBetween(LocalDateTime dateStart, LocalDateTime dateEnd) {
        log.debug("Finding appointments between {} and {}", dateStart, dateEnd);
        List<AppointmentDto> appointmentsFound = AppointmentToAppointmentDtoMapper
                .appointmentToAppointmentDtoMapper
                .mapAppointmentsToDtos(appointmentRepository.findByDateStartBetween(dateStart, dateEnd));
        if (appointmentsFound.isEmpty()) {
            throw new AppointmentNotFoundException(ErrorMessages.APPOINTMENT_NOT_FOUND);
        }
        log.debug("Found {} appointments between {} and {}", appointmentsFound.size(), dateStart, dateEnd);
        return appointmentsFound;
    }

}
