package com.biblioteca.biblioteca_digital.controller;
import com.biblioteca.biblioteca_digital.models.Calificacion;
import com.biblioteca.biblioteca_digital.service.CalificacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/calificaciones")
public class CalificacionController {

    private final CalificacionService calificacionService;

    public CalificacionController(CalificacionService calificacionService) {
        this.calificacionService = calificacionService;
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping
    public ResponseEntity<Calificacion> calificar(@RequestBody Calificacion calificacion) {
        return ResponseEntity.ok(calificacionService.save(calificacion));
    }

    @GetMapping("/calificaciones/promedio/{libroId}")
    public ResponseEntity<Double> obtenerPromedio(@PathVariable Long libroId) {
        double promedio = calificacionService.obtenerPromedioCalificacion(libroId);
        return ResponseEntity.ok(promedio);
    }

    @GetMapping("/promedio/{libroId}")
    public ResponseEntity<Double> promedio(@PathVariable Long libroId) {
        return ResponseEntity.ok(calificacionService.getPromedioPorLibro(libroId));
    }
}

