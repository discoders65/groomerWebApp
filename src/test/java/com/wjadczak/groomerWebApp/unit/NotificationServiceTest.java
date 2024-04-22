package com.wjadczak.groomerWebApp.unit;

import com.wjadczak.groomerWebApp.configuration.TestClock;
import com.wjadczak.groomerWebApp.configuration.TestUtils;
import com.wjadczak.groomerWebApp.dto.NotificationDto;
import com.wjadczak.groomerWebApp.service.implementation.NotificationServiceImpl;
import com.wjadczak.groomerWebApp.utils.MessageType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.time.Clock;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class NotificationServiceTest {

    private Clock clock;
    @Mock
    private RabbitTemplate rabbitTemplate;
    private NotificationServiceImpl notificationService;
    @BeforeEach
    void setUp(){
        clock = TestClock.getFixedClock();
        notificationService = new NotificationServiceImpl(rabbitTemplate, clock);
    }

    @Test
    public void verifyIfRegistrationNotificationSend(){

        notificationService.sendRegistrationNotification(TestUtils.NON_EXITENT_EMAIL);
        NotificationDto notificationDto = NotificationDto.builder()
                .eventDate(TestUtils.FIXED_DATE_TIME)
                .receiverMail(TestUtils.NON_EXITENT_EMAIL)
                .type(MessageType.REGISTRATION_CONFIRMATION)
                .build();

        verify(rabbitTemplate).convertAndSend(
                eq(null),
                eq(null),
                eq(notificationDto));
    }

    @Test
    void sendAppointmentCancellationNotification() {
        String email = TestUtils.NON_EXITENT_EMAIL;
        LocalDateTime bookingDate = TestUtils.FIXED_DATE_TIME;

        notificationService.sendAppointmentCancellationNotification(email, bookingDate);

        NotificationDto notificationDto = NotificationDto.builder()
                .eventDate(bookingDate)
                .receiverMail(email)
                .type(MessageType.APPOINTMENT_CANCELLATION)
                .build();

        verify(rabbitTemplate).convertAndSend(
                eq(null),
                eq(null),
                eq(notificationDto));
    }

    @Test
    void sendAppointmentConfirmationNotification() {
        String email = TestUtils.NON_EXITENT_EMAIL;
        LocalDateTime bookingDate = TestUtils.FIXED_DATE_TIME;

        notificationService.sendAppointmentConfirmationNotification(email, bookingDate);

        NotificationDto notificationDto = NotificationDto.builder()
                .eventDate(bookingDate)
                .receiverMail(email)
                .type(MessageType.APPOINTMENT_CONFIRMATION)
                .build();

        verify(rabbitTemplate).convertAndSend(
                eq(null),
                eq(null),
                eq(notificationDto));
    }

}
