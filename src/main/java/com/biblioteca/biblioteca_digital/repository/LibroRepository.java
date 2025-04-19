package com.biblioteca.biblioteca_digital.repository;

import com.biblioteca.biblioteca_digital.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LibroRepository extends JpaRepository<Libro,Long> {
    List<Libro> findByGenero(String genero);
    List<Libro> findByAutorContainingIgnoreCase(String autor);
    List<Libro> findByTituloContainingIgnoreCase(String titulo);
    List<Libro> findByGeneroIgnoreCase(String genero);
}
