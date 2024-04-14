package com.wjadczak.groomerWebApp.dto;

import com.wjadczak.groomerWebApp.utils.MessageType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class NotificationDto {
    @NotNull
    private MessageType type;
    @NotNull
    @Email
    private String receiverMail;
    @NotNull
    private LocalDateTime eventDate;
}
