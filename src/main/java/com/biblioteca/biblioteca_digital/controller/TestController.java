package com.biblioteca.biblioteca_digital.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/protegido")
    public String zonaProtegida() {
        return "Accediste a un endpoint protegido correctamente ✅";
    }
}
