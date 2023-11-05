package com.wjadczak.groomerWebApp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor

@Table(name ="roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String name;
    @OneToMany(mappedBy = "role")
    private Set<UserEntity> users = new HashSet<>();

//    public void setName(String name) {
//        this.name = name;
//    }
}
