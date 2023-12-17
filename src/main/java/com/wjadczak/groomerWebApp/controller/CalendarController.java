package com.wjadczak.groomerWebApp.controller;


import com.wjadczak.groomerWebApp.controller.dto.AppointmentDto;
import com.wjadczak.groomerWebApp.errors.InvalidSearchRequestException;
import com.wjadczak.groomerWebApp.request.AppointmentSearchRequest;
import com.wjadczak.groomerWebApp.service.AppointmentService;
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
    public ResponseEntity<List<AppointmentDto>> findAppointment(@RequestBody AppointmentSearchRequest appointmentSearchRequest) throws InvalidSearchRequestException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(appointmentService
                        .findAppointment(appointmentSearchRequest));
    }


}
