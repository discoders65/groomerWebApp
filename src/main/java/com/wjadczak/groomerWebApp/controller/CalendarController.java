package com.wjadczak.groomerWebApp.controller;


import com.wjadczak.groomerWebApp.controller.dto.AppointmentDto;
import com.wjadczak.groomerWebApp.errors.InvalidRequestException;
import com.wjadczak.groomerWebApp.request.SearchRequest;
import com.wjadczak.groomerWebApp.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/v1/calendar")
@RequiredArgsConstructor
public class CalendarController {

    @Autowired
    AppointmentService appointmentService;

    @PostMapping("/search")
    public ResponseEntity<List<AppointmentDto>> findAppointment(@RequestBody SearchRequest searchRequest) throws InvalidRequestException {

        try{
            appointmentService.validateDateTimeInput(searchRequest);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime startDateTime = LocalDateTime.parse(searchRequest.getStartDateTime(), formatter);
            LocalDateTime endDateTime = LocalDateTime.parse(searchRequest.getEndDateTime(), formatter);

            return ResponseEntity.ok(appointmentService.findAppointmentDateBetween(startDateTime, endDateTime));

        }catch (InvalidRequestException exception){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }

    }


}
