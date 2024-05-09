package com.wjadczak.groomerWebApp.repository;

import com.wjadczak.groomerWebApp.entity.UtilityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UtilityRepository extends JpaRepository<UtilityEntity, UUID> {
}
