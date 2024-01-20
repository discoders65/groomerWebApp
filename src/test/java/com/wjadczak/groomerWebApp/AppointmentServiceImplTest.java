package com.wjadczak.groomerWebApp;

import com.wjadczak.groomerWebApp.dto.AppointmentDto;
import com.wjadczak.groomerWebApp.dto.AppointmentSearchRequestDto;
import com.wjadczak.groomerWebApp.entity.AppointmentEntity;
import com.wjadczak.groomerWebApp.entity.UserEntity;
import com.wjadczak.groomerWebApp.errors.AppointmentNotFoundException;
import com.wjadczak.groomerWebApp.errors.InvalidSearchRequestException;
import com.wjadczak.groomerWebApp.repository.AppointmentRepository;
import com.wjadczak.groomerWebApp.service.implementation.AppointmentServiceImpl;
import com.wjadczak.groomerWebApp.utils.TimeParserUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class AppointmentServiceImplTest {
    @Mock
    private AppointmentRepository appointmentRepositoryMock;
    @InjectMocks
    private AppointmentServiceImpl appointmentService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void shouldThrowAppointmentNotFoundExceptionIfNoAppointmentFound() {
        // given
        AppointmentSearchRequestDto requestDto = new AppointmentSearchRequestDto();
        requestDto.setStartDateTime("1024-01-14 10:00:00");
        requestDto.setEndDateTime("1024-01-14 12:00:00");
        LocalDateTime startDateTime = TimeParserUtil.parseDateTime(requestDto.getStartDateTime());
        LocalDateTime endDateTime = TimeParserUtil.parseDateTime(requestDto.getEndDateTime());
        // when
        Mockito.when(appointmentRepositoryMock.findByDateStartBetween(startDateTime, endDateTime))
                .thenReturn(Collections.emptyList());
        // then
        Assertions.assertThrows(AppointmentNotFoundException.class, () -> appointmentService.findAppointment(requestDto));
    }

    @Test
    void shouldThrowInvalidSearchRequestExceptionIfAppointmentSearchRequestDtoIsNull(){
        // given
        AppointmentSearchRequestDto requestDto = null;
        // when & then
        Assertions.assertThrows(InvalidSearchRequestException.class, () ->appointmentService.findAppointment(requestDto));
    }

    @Test
    void shouldReturnAppointmentListIfReceivedValidInput(){
        // given
        AppointmentSearchRequestDto requestDtoMock = Mockito.mock(AppointmentSearchRequestDto.class);
        Mockito.when(requestDtoMock.getStartDateTime()).thenReturn("2024-01-14 12:00:00");
        Mockito.when(requestDtoMock.getEndDateTime()).thenReturn("2024-01-14 14:00:00");

        LocalDateTime startDateTime = LocalDateTime.of(2024, 1, 14, 12, 0,0);
        LocalDateTime endDateTime = LocalDateTime.of(2024, 1, 14, 14, 0,0);

        List<AppointmentEntity> appointmentsMock = Arrays.asList(
                new AppointmentEntity(
                        UUID.randomUUID(),
                        startDateTime,
                        endDateTime,
                        "comment",
                        Mockito.mock(UserEntity.class),
                        new BigDecimal("100"),
                        false));

        // when
        Mockito.when(
                appointmentRepositoryMock
                .findByDateStartBetween(startDateTime, endDateTime))
                .thenReturn(appointmentsMock);

        List<AppointmentDto> result = appointmentService.findAppointment(requestDtoMock);
        // then
        Assertions.assertEquals(appointmentsMock.size(), result.size());
    }

    @Test
    void shouldThrowInvalidSearchRequestExceptionIfStartDateOrEndDateIsNull(){
        // Test case 1: Start date is null, end date is not null

        // given
        AppointmentSearchRequestDto requestDto1 = Mockito.mock(AppointmentSearchRequestDto.class);

        // when
        Mockito.when(requestDto1.getStartDateTime()).thenReturn(null);
        Mockito.when(requestDto1.getEndDateTime()).thenReturn("2024-01-14 14:00:00");

        // then
        Assertions.assertThrows(InvalidSearchRequestException.class,
                () -> appointmentService.findAppointment(requestDto1));

        // Test case 2: Start date is not null, end date is null

        // given
        AppointmentSearchRequestDto requestDto2 = Mockito.mock(AppointmentSearchRequestDto.class);

        // when
        Mockito.when(requestDto2.getStartDateTime()).thenReturn("2024-01-14 12:00:00");
        Mockito.when(requestDto2.getEndDateTime()).thenReturn(null);

        // then
        Assertions.assertThrows(InvalidSearchRequestException.class,
                () -> appointmentService.findAppointment(requestDto2));

        // Test case 3: Both start date and end date are null
        AppointmentSearchRequestDto requestDto3 = Mockito.mock(AppointmentSearchRequestDto.class);
        Mockito.when(requestDto3.getStartDateTime()).thenReturn(null);
        Mockito.when(requestDto3.getEndDateTime()).thenReturn(null);
        Assertions.assertThrows(InvalidSearchRequestException.class,
                () -> appointmentService.findAppointment(requestDto3));
    }

}
