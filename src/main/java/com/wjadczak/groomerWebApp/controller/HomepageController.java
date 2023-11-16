package com.wjadczak.groomerWebApp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/home")
@RequiredArgsConstructor
public class HomepageController {

    @GetMapping("/")
    public String home(){
        return "homepage";
    }

}
