package com.wjadczak.groomerWebApp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "exemplar")
@Getter
@Setter
public class Exemplar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exemplar_id")
    private long id;
    @Column(name = "exemplar_field")
    private String field;



}
