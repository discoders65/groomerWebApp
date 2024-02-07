package com.wjadczak.groomerWebApp.repository;

import com.wjadczak.groomerWebApp.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, UUID> {

    List<AppointmentEntity> findByDateStartBetween(LocalDateTime dateStart, LocalDateTime dateEnd);
    @Query("SELECT i.userEntity.id FROM AppointmentEntity i WHERE i.id = :appointmentId ")
    Optional<UUID> findUserIdByAppointmentId(@Param("appointmentId")UUID appointmentId);

}
