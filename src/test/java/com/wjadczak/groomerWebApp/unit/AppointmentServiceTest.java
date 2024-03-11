package com.wjadczak.groomerWebApp.unit;

import com.wjadczak.groomerWebApp.TestUtils;
import com.wjadczak.groomerWebApp.dto.AppointmentDto;
import com.wjadczak.groomerWebApp.dto.AppointmentSearchRequestDto;
import com.wjadczak.groomerWebApp.dto.CancelAppointmentDto;
import com.wjadczak.groomerWebApp.errors.AppointmentNotFoundException;
import com.wjadczak.groomerWebApp.repository.AppointmentRepository;
import com.wjadczak.groomerWebApp.service.implementation.AppointmentServiceImpl;
import com.wjadczak.groomerWebApp.service.validators.AppointmentServiceValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AppointmentServiceTest {
    @Mock
    private AppointmentRepository appointmentRepositoryMock;
    @Mock
    private AppointmentServiceValidator appointmentServiceValidator;
    @InjectMocks
    private AppointmentServiceImpl appointmentService;

    @Test
    void shouldThrowAppointmentNotFoundExceptionIfGivenWrongId() {
        // given
        CancelAppointmentDto cancelDto = new CancelAppointmentDto(TestUtils.NON_EXISTENT_APPOINTMENT_ID);
        // when
        doNothing().when(appointmentServiceValidator).validateCancelAppointmentDto(cancelDto);
        when(appointmentRepositoryMock.findById(cancelDto.getAppointmentId())).thenReturn(Optional.empty());
        // then
        Assertions.assertThrows(AppointmentNotFoundException.class, () -> appointmentService.cancelCurrentUserAppointment(cancelDto));

    }

    @Test
    void shouldReturnAppointmentListIfReceivedValidInput() {
        // given
        AppointmentSearchRequestDto requestDtoMock = new AppointmentSearchRequestDto(TestUtils.VALID_APPOINTMENT_START_DATE, TestUtils.VALID_APPOINTMENT_END_DATE);
        // when
        when(appointmentRepositoryMock
                .findByDateStartBetween(TestUtils.VALID_START_DATE_TIME, TestUtils.VALID_END_DATE_TIME))
                .thenReturn(TestUtils.TEST_APPOINTMENTS);

        List<AppointmentDto> result = appointmentService.findAppointment(requestDtoMock);
        // then
        Assertions.assertEquals(TestUtils.TEST_APPOINTMENTS.size(), result.size());
    }

}
