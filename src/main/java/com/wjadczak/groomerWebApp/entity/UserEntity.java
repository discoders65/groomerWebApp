package com.wjadczak.groomerWebApp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @ManyToOne
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")}
    )
    private Role role;

    public void setRole(Role role) {
        this.role = role;
    }
}
