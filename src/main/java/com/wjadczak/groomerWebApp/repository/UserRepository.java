package com.wjadczak.groomerWebApp.repository;

import com.wjadczak.groomerWebApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByUserName(String userName);
}
