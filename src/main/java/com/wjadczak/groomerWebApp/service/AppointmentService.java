package com.wjadczak.groomerWebApp.service;

import com.wjadczak.groomerWebApp.controller.dto.AppointmentDto;
import com.wjadczak.groomerWebApp.request.AppointmentSearchRequest;

import java.util.List;


public interface AppointmentService {

    List<AppointmentDto> findAppointment(AppointmentSearchRequest appointmentSearchRequest);

}
