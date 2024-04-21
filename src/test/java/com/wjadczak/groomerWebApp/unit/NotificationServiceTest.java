package com.wjadczak.groomerWebApp.unit;

import com.wjadczak.groomerWebApp.configuration.TestUtils;
import com.wjadczak.groomerWebApp.dto.NotificationDto;
import com.wjadczak.groomerWebApp.service.implementation.NotificationServiceImpl;
import com.wjadczak.groomerWebApp.utils.MessageType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class NotificationServiceTest {

    private Clock fixedClock;
    @Mock
    private RabbitTemplate rabbitTemplate;
    @Mock
    Clock clock;
    @InjectMocks
    private NotificationServiceImpl notificationService;
    @BeforeEach
    public void setUp(){
        fixedClock = Clock.fixed(
                TestUtils
                        .FIXED_DATE_TIME
                        .atZone(ZoneId.systemDefault())
                        .toInstant(), ZoneId.systemDefault());
        when(clock.instant()).thenReturn(fixedClock.instant());
        when(clock.getZone()).thenReturn(fixedClock.getZone());
  }

    @Test
    public void verifyIfRegistrationNotificationSend(){
        notificationService.sendRegistrationNotification(TestUtils.NON_EXITENT_EMAIL);
        NotificationDto notificationDto = NotificationDto.builder()
                .eventDate(LocalDateTime.now())
                .receiverMail(TestUtils.NON_EXITENT_EMAIL)
                .type(MessageType.REGISTRATION_CONFIRMATION)
                .build();

        verify(rabbitTemplate).convertAndSend(
                eq(null),
                eq(null),
                eq(notificationDto));


    }

}
