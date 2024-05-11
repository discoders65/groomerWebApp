package com.wjadczak.groomerWebApp.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.UUID;
@Entity
@Table(name = "utility")
@Getter
public class UtilityEntity {

    public UtilityEntity(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private String name;
    private String description;
    @Column(columnDefinition = "NUMERIC(10,2)", name = "low_price")
    private BigDecimal lowPrice;
    @Column(columnDefinition = "NUMERIC(10,2)", name = "up_price")
    private BigDecimal upPrice;
    @Column(name = "execution_time")
    private LocalTime executionTime;
    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(name = "appointment_utility", joinColumns = @JoinColumn(name = "utility_id"),
            inverseJoinColumns = @JoinColumn(name = "appointment_id"))
    private HashSet<AppointmentEntity> appointments;

}
