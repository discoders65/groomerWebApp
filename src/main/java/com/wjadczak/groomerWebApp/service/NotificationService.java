package com.wjadczak.groomerWebApp.service;

import java.time.LocalDateTime;

public interface NotificationService {
    void sendRegistrationNotification(String email);
    void sendAppointmentCancellationNotification(String email, LocalDateTime bookingDate);
    void sendAppointmentConfirmationNotification(String email, LocalDateTime bookingDate);
}
