package com.biblioteca.biblioteca_digital.controller;


import com.biblioteca.biblioteca_digital.models.Resena;
import com.biblioteca.biblioteca_digital.service.ResenaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resenas")
public class ResenaController {

    private final ResenaService resenaService;

    public ResenaController(ResenaService resenaService) {
        this.resenaService = resenaService;
    }

    @GetMapping("/resenas/{libroId}")
    public ResponseEntity<List<Resena>> obtenerResenas(@PathVariable Long libroId) {
        List<Resena> resenas = resenaService.obtenerResenasDeLibro(libroId);
        return ResponseEntity.ok(resenas);
    }


    @GetMapping("/libro/{libroId}")
    public ResponseEntity<List<Resena>> getPorLibro(@PathVariable Long libroId) {
        return ResponseEntity.ok(resenaService.findByLibroId(libroId));
    }
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping
    public ResponseEntity<Resena> crear(@RequestBody Resena resena) {
        return ResponseEntity.ok(resenaService.save(resena));
    }

}

