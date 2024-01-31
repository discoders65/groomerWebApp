package com.wjadczak.groomerWebApp;

import com.wjadczak.groomerWebApp.dto.AppointmentDto;
import com.wjadczak.groomerWebApp.dto.AppointmentSearchRequestDto;
import com.wjadczak.groomerWebApp.dto.CancelAppointmentDto;
import com.wjadczak.groomerWebApp.errors.AppointmentNotFoundException;
import com.wjadczak.groomerWebApp.repository.AppointmentRepository;
import com.wjadczak.groomerWebApp.service.implementation.AppointmentServiceImpl;
import com.wjadczak.groomerWebApp.utils.TimeParserUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AppointmentServiceTest {
    @Mock
    private AppointmentRepository appointmentRepositoryMock;
    @InjectMocks
    private AppointmentServiceImpl appointmentService;

    @Test
    void shouldThrowAppointmentNotFoundExceptionIfGivenWrongId(){
        // given
        CancelAppointmentDto cancelDto = new CancelAppointmentDto(TestUtils.NON_EXISTENT_APPOINTMENT_ID);
        // when
        when(appointmentRepositoryMock.findById(cancelDto.getAppointmentId())).thenReturn(Optional.empty());
        // then
        Assertions.assertThrows(AppointmentNotFoundException.class, () -> appointmentService.cancelAppointment(cancelDto));

    }
    @Test
    void shouldThrowAppointmentNotFoundExceptionIfNoAppointmentFound() {
        // given
        AppointmentSearchRequestDto requestDto = new AppointmentSearchRequestDto(TestUtils.INVALID_APPOINTMENT_DATE, TestUtils.INVALID_APPOINTMENT_DATE);
        LocalDateTime startDateTime = TimeParserUtil.parseDateTime(requestDto.getStartDateTime());
        LocalDateTime endDateTime = TimeParserUtil.parseDateTime(requestDto.getEndDateTime());
        // when
        when(appointmentRepositoryMock.findByDateStartBetween(startDateTime, endDateTime))
                .thenReturn(Collections.emptyList());
        // then
        Assertions.assertThrows(AppointmentNotFoundException.class, () -> appointmentService.findAppointment(requestDto));
    }

    @Test
    void shouldReturnAppointmentListIfReceivedValidInput() {
        // given
        AppointmentSearchRequestDto requestDtoMock = new AppointmentSearchRequestDto(TestUtils.VALID_APPOINTMENT_START_DATE, TestUtils.VALID_APPOINTMENT_END_DATE);
        // when
        when(
                        appointmentRepositoryMock
                                .findByDateStartBetween(TestUtils.VALID_START_DATE_TIME, TestUtils.VALID_END_DATE_TIME))
                .thenReturn(TestUtils.VALID_APPOINTMENT);

        List<AppointmentDto> result = appointmentService.findAppointment(requestDtoMock);
        // then
        Assertions.assertEquals(TestUtils.VALID_APPOINTMENT.size(), result.size());
    }


}
