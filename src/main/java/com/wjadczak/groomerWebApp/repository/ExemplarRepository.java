package com.wjadczak.groomerWebApp.repository;

import com.wjadczak.groomerWebApp.entity.Exemplar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExemplarRepository extends JpaRepository<Exemplar, Long> {

    List<Exemplar> findAllByField(String field);
}
