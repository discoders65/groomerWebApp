package com.wjadczak.groomerWebApp.repository;

import com.wjadczak.groomerWebApp.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, UUID> {

    List<AppointmentEntity> findByDateStartBetween(LocalDateTime dateStart, LocalDateTime dateEnd);
}
