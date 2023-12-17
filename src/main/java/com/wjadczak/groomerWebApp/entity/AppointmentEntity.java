package com.wjadczak.groomerWebApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="appointment")
@Getter
public class AppointmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @Column(name ="date_start")
    private LocalDateTime dateStart;
    @Column(name = "date_end")
    private LocalDateTime dateEnd;
    @Column
    private String comment;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity userEntity;
    @Column
    private Double pricing;
    @Column
    private boolean accepted;
}
