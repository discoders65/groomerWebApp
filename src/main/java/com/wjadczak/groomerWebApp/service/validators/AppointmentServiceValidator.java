package com.wjadczak.groomerWebApp.service.validators;

import com.wjadczak.groomerWebApp.config.security.AuthenticationHelper;
import com.wjadczak.groomerWebApp.dto.AppointmentSaveRequestDto;
import com.wjadczak.groomerWebApp.dto.AppointmentSearchRequestDto;
import com.wjadczak.groomerWebApp.dto.CancelAppointmentDto;
import com.wjadczak.groomerWebApp.errors.ErrorMessages;
import com.wjadczak.groomerWebApp.errors.InvalidSaveAppointmentDataInputException;
import com.wjadczak.groomerWebApp.errors.InvalidSearchRequestException;
import com.wjadczak.groomerWebApp.errors.InvalidUserDataInputException;
import com.wjadczak.groomerWebApp.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppointmentServiceValidator {

    private final AuthenticationHelper authenticationHelper;
    private final AppointmentRepository appointmentRepository;

    public void validateCancelAppointmentDto(CancelAppointmentDto cancelAppointmentDto) {
        if (nonNull(cancelAppointmentDto)) {
            boolean appointmentIdIsNull = isNull(cancelAppointmentDto.getAppointmentId());
            if (appointmentIdIsNull) {
                throw new InvalidSearchRequestException(ErrorMessages.NULL_INPUT);
            } else if (!currentUserOwnsAppointment(cancelAppointmentDto)) {
                log.error("Trying to cancel appointment not owned by current user.");
                throw new InvalidSearchRequestException(ErrorMessages.APPOINTMENT_NOT_OWNED);
            }
        } else {
            log.error("Received null input for cancelAppointmentDto");
            throw new InvalidSearchRequestException(ErrorMessages.NULL_INPUT);
        }
    }

    private boolean currentUserOwnsAppointment(CancelAppointmentDto cancelAppointmentDto) {
        UUID appointmentUserId = appointmentRepository
                .findUserIdByAppointmentId(cancelAppointmentDto.getAppointmentId())
                .orElseThrow(() -> new InvalidUserDataInputException(ErrorMessages.INVALID_ID));
        UUID currentUserId = authenticationHelper.getCurrentUser().getId();

        log.debug("AppointmentUserId: " + appointmentUserId + " ,CurrentUserId: " + currentUserId);
        return appointmentUserId.equals(currentUserId);
    }

    public void validateAppointmentSearchData(AppointmentSearchRequestDto appointmentSearchRequestDto) {
        log.debug("Validating date-time input: {}", appointmentSearchRequestDto);
        if (nonNull(appointmentSearchRequestDto)) {
            checkIfSearchDataInputIsNull(appointmentSearchRequestDto);
        } else {
            log.error("Invalid search request: null input");
            throw new InvalidSearchRequestException(ErrorMessages.MISSING_SEARCH_INPUT);
        }
    }

    private void checkIfSearchDataInputIsNull(AppointmentSearchRequestDto appointmentSearchRequestDto) {
        String startDateTime = appointmentSearchRequestDto.getStartDateTime();
        String endDateTime = appointmentSearchRequestDto.getEndDateTime();
        boolean startDateIsNullOrEndDateIsNull = isNull(startDateTime) || isNull(endDateTime);
        if (startDateIsNullOrEndDateIsNull) {
            log.error("Invalid search request: Missing startDateTime or endDateTime");
            throw new InvalidSearchRequestException(ErrorMessages.MISSING_SEARCH_INPUT);
        }
    }

    public void validateSaveAppointmentDataInput(AppointmentSaveRequestDto appointmentSaveRequestDto) {
        if (nonNull(appointmentSaveRequestDto)) {
            String startDateTime = appointmentSaveRequestDto.getStartDateTime();
            boolean startDateTimeIsNull = isNull(startDateTime);
            if (startDateTimeIsNull) {
                throw new InvalidSaveAppointmentDataInputException(ErrorMessages.INVALID_APPOINTMENT_DATE);
            }
        } else {
            throw new InvalidSaveAppointmentDataInputException(ErrorMessages.INVALID_APPOINTMENT_DATE);
        }
    }
}
