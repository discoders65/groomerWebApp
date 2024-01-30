package com.wjadczak.groomerWebApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppointmentDto {

    private UUID id;
    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;
    private String comment;
    private UUID userId;
    private Double pricing;
    private boolean accepted;

}
