package com.wjadczak.groomerWebApp.controller;

import com.wjadczak.groomerWebApp.entity.Exemplar;
import com.wjadczak.groomerWebApp.service.ExemplarService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api")
public class ExemplarController {

    private final ExemplarService exemplarService;

    public ExemplarController(ExemplarService exemplarService) {
        this.exemplarService = exemplarService;
    }

    @GetMapping("/exemplar")
    public List<Exemplar> getExemplars(){
        return exemplarService.getExemplar();
    }

    @GetMapping("/exemplar/{field}")
    public List<Exemplar> getExemplarsByField(@PathVariable("field") String field){
        return exemplarService.getExemplarByField(field);
    }

    @PostMapping("/exemplar")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTeacher(@RequestBody Exemplar exemplar){
        exemplarService.saveExemplar(exemplar);
    }
}
