package com.wjadczak.groomerWebApp.controller;


import com.wjadczak.groomerWebApp.dto.AppointmentDto;
import com.wjadczak.groomerWebApp.dto.AppointmentSearchRequestDto;
import com.wjadczak.groomerWebApp.dto.AppointmentSaveRequestDto;
import com.wjadczak.groomerWebApp.errors.InvalidSaveAppointmentDataInputException;
import com.wjadczak.groomerWebApp.errors.InvalidSearchRequestException;
import com.wjadczak.groomerWebApp.service.AppointmentService;
import com.wjadczak.groomerWebApp.utils.InfoMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/calendar")
@RequiredArgsConstructor
public class CalendarController {


    private final AppointmentService appointmentService;

    @PostMapping("/search")
    public ResponseEntity<List<AppointmentDto>> findAppointment(@RequestBody AppointmentSearchRequestDto appointmentSearchRequestDto) throws InvalidSearchRequestException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(appointmentService
                        .findAppointment(appointmentSearchRequestDto));
    }
    @PostMapping("/save")
    public ResponseEntity<String> saveAppointment(@RequestBody AppointmentSaveRequestDto appointmentSaveRequestDto) throws InvalidSaveAppointmentDataInputException {
        appointmentService.saveAppointment(appointmentSaveRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(InfoMessages.APPOINTMENT_SAVED);
    }

}
