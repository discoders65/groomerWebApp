package com.wjadczak.groomerWebApp.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppointmentDto {

    private Long id;
    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;
    private String comment;
    private Long userId;
    private Double pricing;
    private boolean accepted;
}
