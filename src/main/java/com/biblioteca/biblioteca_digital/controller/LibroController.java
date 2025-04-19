package com.biblioteca.biblioteca_digital.controller;

import com.biblioteca.biblioteca_digital.models.Libro;
import com.biblioteca.biblioteca_digital.service.LibroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    public ResponseEntity<List<Libro>> getAll() {
        return ResponseEntity.ok(libroService.findAll());
    }

    @GetMapping("/buscar/titulo")
    public ResponseEntity<List<Libro>> buscarPorTitulo(@RequestParam String titulo) {
        List<Libro> libros = libroService.buscarPorTitulo(titulo);
        return ResponseEntity.ok(libros);
    }
    @GetMapping("/buscar/autor")
    public ResponseEntity<List<Libro>> buscarPorAutor(@RequestParam String autor) {
        List<Libro> libros = libroService.buscarPorAutor(autor);
        return ResponseEntity.ok(libros);
    }
    @GetMapping("/filtrar/genero")
    public ResponseEntity<List<Libro>> filtrarPorGenero(@RequestParam String genero) {
        List<Libro> libros = libroService.filtrarPorGenero(genero);
        return ResponseEntity.ok(libros);
    }

    @PostMapping
    public ResponseEntity<Libro> create(@RequestBody Libro libro) {
        return ResponseEntity.ok(libroService.save(libro));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> getById(@PathVariable Long id) {
        return ResponseEntity.ok(libroService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> update(@PathVariable Long id, @RequestBody Libro libro) {
        libro.setId(id);
        return ResponseEntity.ok(libroService.save(libro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        libroService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
