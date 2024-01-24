package com.wjadczak.groomerWebApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CancelAppointmentDto {
    private UUID appointmentId;
}

