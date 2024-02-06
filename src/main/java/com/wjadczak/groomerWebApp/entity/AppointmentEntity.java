package com.wjadczak.groomerWebApp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="appointment")
@Getter
@Setter
@Builder
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
    @Column(columnDefinition = "NUMERIC(10,2)")
    private BigDecimal pricing; //BigDecimal
    @Column
    private boolean accepted;
    @Column
    private boolean cancelled;
}
