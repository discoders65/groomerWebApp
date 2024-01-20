package com.wjadczak.groomerWebApp;

import com.wjadczak.groomerWebApp.dto.AppointmentSearchRequestDto;
import com.wjadczak.groomerWebApp.errors.InvalidSearchRequestException;
import com.wjadczak.groomerWebApp.service.validators.AppointmentServiceValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AppointmentServiceValidatorTest {
    @InjectMocks
    private AppointmentServiceValidator appointmentServiceValidator;

    @Test
    void shouldThrowInvalidSearchRequestExceptionIfStartDate() {
        // given
        AppointmentSearchRequestDto requestDto = new AppointmentSearchRequestDto(null, TestUtils.VALID_APPOINTMENT_START_DATE);
        // then
        Assertions.assertThrows(InvalidSearchRequestException.class,
                () -> appointmentServiceValidator.validateAppointmentSearchData(requestDto));

    }

    @Test
    void shouldThrowInvalidSearchRequestExceptionIfEndDateIsNull() {
        // given
        AppointmentSearchRequestDto requestDto = new AppointmentSearchRequestDto(TestUtils.VALID_APPOINTMENT_END_DATE, null);
        // then
        Assertions.assertThrows(InvalidSearchRequestException.class,
                () -> appointmentServiceValidator.validateAppointmentSearchData(requestDto));

    }

    @Test
    void shouldThrowInvalidSearchRequestExceptionIfStartDateAndEndDateIsNull() {
        // given
        AppointmentSearchRequestDto requestDto = new AppointmentSearchRequestDto(null, null);
        // then
        Assertions.assertThrows(InvalidSearchRequestException.class,
                () -> appointmentServiceValidator.validateAppointmentSearchData(requestDto));
    }

    @Test
    void shouldThrowInvalidSearchRequestExceptionIfAppointmentSearchRequestDtoIsNull() {
        // given
        AppointmentSearchRequestDto requestDto = null;
        // then
        Assertions.assertThrows(InvalidSearchRequestException.class, () -> appointmentServiceValidator.validateAppointmentSearchData(requestDto));
    }
}
