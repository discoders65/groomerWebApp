package com.wjadczak.groomerWebApp.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppointmentDto {

    private UUID id;
    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;
    private String comment;
    private UUID userId;
    private Double pricing;
    private boolean accepted;

}
