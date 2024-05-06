package com.wjadczak.groomerWebApp.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.UUID;
@Entity
@Table(name = "service")
public class ServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private String type;
    private String description;
    @Column(columnDefinition = "NUMERIC(10,2)", name = "low_price")
    private BigDecimal lowPrice;
    @Column(columnDefinition = "NUMERIC(10,2)", name = "up_price")
    private BigDecimal upPrice;
    @Column(name = "execution_time")
    private LocalTime executionTime;
    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(name = "appointment_service", joinColumns = @JoinColumn(name = "service_id"),
            inverseJoinColumns = @JoinColumn(name = "appointment_id"))
    private HashSet<AppointmentEntity> appointments;

}
