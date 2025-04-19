package com.biblioteca.biblioteca_digital.repository;

import com.biblioteca.biblioteca_digital.models.Progreso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgresoRepository extends JpaRepository<Progreso, Long> {
    List<Progreso> findByUsuarioId(Long usuarioId);
}

