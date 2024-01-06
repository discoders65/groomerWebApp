package com.wjadczak.groomerWebApp.service;

import com.wjadczak.groomerWebApp.dto.AppointmentDto;
import com.wjadczak.groomerWebApp.dto.AppointmentSearchRequestDto;

import java.util.List;


public interface AppointmentService {

    List<AppointmentDto> findAppointment(AppointmentSearchRequestDto appointmentSearchRequestDto);

}
