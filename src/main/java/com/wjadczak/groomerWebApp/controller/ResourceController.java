package com.wjadczak.groomerWebApp.controller;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/resource")
@RequiredArgsConstructor
@Hidden
public class ResourceController {
    @GetMapping
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("your resource");
    }
}