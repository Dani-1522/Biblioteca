package com.biblioteca.biblioteca_digital.service;


import com.biblioteca.biblioteca_digital.models.Calificacion;
import com.biblioteca.biblioteca_digital.repository.CalificacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalificacionService {

    private final CalificacionRepository repo;

    public CalificacionService(CalificacionRepository repo) {
        this.repo = repo;
    }

    public Calificacion save(Calificacion calificacion) {
        return repo.save(calificacion);
    }
    public double obtenerPromedioCalificacion(Long libroId) {
        List<Calificacion> calificaciones = repo.findByLibroId(libroId);
        return calificaciones.stream()
                .mapToInt(Calificacion::getPuntuacion)
                .average()
                .orElse(0.0);
    }


    public Double getPromedioPorLibro(Long libroId) {
        List<Calificacion> calificaciones = repo.findByLibroId(libroId);
        if (calificaciones.isEmpty()) return 0.0;

        double total = calificaciones.stream().mapToDouble(Calificacion::getPuntuacion).sum();
        return total / calificaciones.size();
    }
}
