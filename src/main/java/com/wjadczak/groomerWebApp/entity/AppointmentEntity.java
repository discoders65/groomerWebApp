package com.wjadczak.groomerWebApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="appointment")
public class AppointmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="date_start")
    private LocalDateTime dateStart;
    @Column(name = "date_end")
    private LocalDateTime dateEnd;
    @Column
    private String comment;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
    @Column
    private Double pricing;
}
