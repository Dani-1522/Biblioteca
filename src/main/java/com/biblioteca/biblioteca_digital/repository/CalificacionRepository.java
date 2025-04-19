package com.biblioteca.biblioteca_digital.repository;

import com.biblioteca.biblioteca_digital.models.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CalificacionRepository extends JpaRepository<Calificacion, Long> {
    List<Calificacion> findByLibroId(Long libroId);
    Optional<Calificacion> findByUsuarioIdAndLibroId(Long usuarioId, Long libroId);
}
