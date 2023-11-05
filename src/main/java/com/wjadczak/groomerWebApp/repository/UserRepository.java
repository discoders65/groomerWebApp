package com.wjadczak.groomerWebApp.repository;

import com.wjadczak.groomerWebApp.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
    UserEntity findByUserName(String userName);
}
