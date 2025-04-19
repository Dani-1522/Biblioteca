package com.biblioteca.biblioteca_digital.service;
import com.biblioteca.biblioteca_digital.models.Libro;
import com.biblioteca.biblioteca_digital.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    private final LibroRepository repo;

    public LibroService(LibroRepository repo) {
        this.repo = repo;
    }

    public List<Libro> findAll() {
        return repo.findAll();
    }

    public List<Libro> buscarPorTitulo(String titulo) {
        return repo.findByTituloContainingIgnoreCase(titulo);
    }

    public List<Libro> buscarPorAutor(String autor) {
        return repo.findByAutorContainingIgnoreCase(autor);
    }

    public List<Libro> filtrarPorGenero(String genero) {
        return repo.findByGeneroIgnoreCase(genero);
    }

    public Libro findById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public Libro save(Libro libro) {
        return repo.save(libro);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}

