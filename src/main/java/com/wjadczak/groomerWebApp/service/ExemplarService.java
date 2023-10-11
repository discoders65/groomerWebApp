package com.wjadczak.groomerWebApp.service;

import com.wjadczak.groomerWebApp.entity.Exemplar;
import com.wjadczak.groomerWebApp.repository.ExemplarRepository;
import lombok.AllArgsConstructor;

import java.util.List;
@AllArgsConstructor
public class ExemplarService {

    private final ExemplarRepository exemplarRepository;

    public ExemplarService(ExemplarRepository exemplarRepository) {
        this.exemplarRepository = exemplarRepository;
    }

    public List<Exemplar> getExemplar(){
        return exemplarRepository.findAll();
    }

    public List<Exemplar> getExemplarByField(String field){
        return exemplarRepository.findAllByField(field);
    }

    public Exemplar saveExemplar(Exemplar exemplar){
        return exemplarRepository.save(exemplar);
    }
}
