package com.wjadczak.groomerWebApp.service.implementation;

import com.wjadczak.groomerWebApp.entity.AppointmentEntity;
import com.wjadczak.groomerWebApp.repository.AppointmentRepository;
import com.wjadczak.groomerWebApp.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

public class AppointmentServiceImplementation implements AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Override
    public List<AppointmentEntity> findAppointmentDateBetween(LocalDateTime dateStart, LocalDateTime dateEnd) {
        return appointmentRepository.findByDateStartBetween(dateStart, dateEnd);
    }
}
