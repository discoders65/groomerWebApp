package com.wjadczak.groomerWebApp.service.implementation;

import com.wjadczak.groomerWebApp.dto.NotificationDto;
import com.wjadczak.groomerWebApp.service.NotificationService;
import com.wjadczak.groomerWebApp.utils.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final RabbitTemplate rabbitTemplate;
    @Value("${spring.rabbitmq.registrationRoutingKey}")
    private String REGISTRATION_ROUTING_KEY;
    @Value("${spring.rabbitmq.confirmationRoutingKey}")
    private String CONFIRMATION_ROUTING_KEY;
    @Value("${spring.rabbitmq.cancellationRoutingKey}")
    private String CANCELLATION_ROUTING_KEY;
    @Value("${spring.rabbitmq.exchangeName}")
    private String EXCHANGE_NAME;

    @Override
    public void sendRegistrationNotification(String email) {
        NotificationDto notificationDto = NotificationDto
                .builder()
                .eventDate(LocalDateTime.now())
                .receiverMail(email)
                .type(MessageType.REGISTRATION_CONFIRMATION)
                .build();
        rabbitTemplate.convertAndSend(EXCHANGE_NAME,REGISTRATION_ROUTING_KEY,notificationDto);
    }

    public void sendAppointmentCancellationNotification(String email, LocalDateTime bookingDate) {
        NotificationDto notificationDto = NotificationDto
                .builder()
                .eventDate(bookingDate)
                .receiverMail(email)
                .type(MessageType.APPOINTMENT_CANCELLATION)
                .build();
        rabbitTemplate.convertAndSend(EXCHANGE_NAME,CANCELLATION_ROUTING_KEY,notificationDto);
    }

    @Override
    public void sendAppointmentConfirmationNotification(String email, LocalDateTime bookingDate) {
        NotificationDto notificationDto = NotificationDto
                .builder()
                .eventDate(bookingDate)
                .receiverMail(email)
                .type(MessageType.APPOINTMENT_CONFIRMATION)
                .build();
        rabbitTemplate.convertAndSend(EXCHANGE_NAME,CONFIRMATION_ROUTING_KEY,notificationDto);
    }
}
