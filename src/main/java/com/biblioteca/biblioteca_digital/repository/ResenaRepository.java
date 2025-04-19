package com.biblioteca.biblioteca_digital.repository;

import com.biblioteca.biblioteca_digital.models.Resena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResenaRepository extends JpaRepository<Resena, Long> {
    List<Resena> findByLibroId(Long libroId);
    List<Resena> findByUsuarioId(Long usuarioId);
}
