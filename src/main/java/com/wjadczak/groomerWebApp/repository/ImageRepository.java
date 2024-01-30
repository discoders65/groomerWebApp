package com.wjadczak.groomerWebApp.repository;

import com.wjadczak.groomerWebApp.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface ImageRepository extends JpaRepository<ImageEntity, UUID> {
    Optional<ImageEntity> findById(UUID id);
    @Query("SELECT i.id FROM ImageEntity i WHERE i.userEntity.id = :userId")
    Optional<UUID> findImageIdByUserId(@Param("userId") UUID userId);
}
