package com.wjadczak.groomerWebApp.repository;

import com.wjadczak.groomerWebApp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
