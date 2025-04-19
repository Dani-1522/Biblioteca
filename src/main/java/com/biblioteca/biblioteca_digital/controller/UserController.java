package com.biblioteca.biblioteca_digital.controller;
import com.biblioteca.biblioteca_digital.models.Libro;
import com.biblioteca.biblioteca_digital.repository.LibroRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
public class UserController {

    private final LibroRepository libroRepo;

    public UserController(LibroRepository libroRepo) {
        this.libroRepo = libroRepo;
    }

    @GetMapping("/libros")
    public ResponseEntity<List<Libro>> verTodosLosLibros() {
        return ResponseEntity.ok(libroRepo.findAll());
    }

    @GetMapping("/libros/{id}")
    public ResponseEntity<Libro> verLibroPorId(@PathVariable Long id) {
        return ResponseEntity.of(libroRepo.findById(id));
    }
}

