package com.wjadczak.groomerWebApp.service;

import com.wjadczak.groomerWebApp.entity.AppointmentEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface AppointmentService {

    public List<AppointmentEntity> findAppointmentDateBetween(LocalDateTime dateStart, LocalDateTime dateEnd);

}
