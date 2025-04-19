package com.biblioteca.biblioteca_digital.controller;
import com.biblioteca.biblioteca_digital.models.Favorito;
import com.biblioteca.biblioteca_digital.models.Libro;
import com.biblioteca.biblioteca_digital.service.FavoritoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favoritos")
public class FavoritoController {

    private final FavoritoService favoritoService;

    public FavoritoController(FavoritoService favoritoService) {
        this.favoritoService = favoritoService;
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<Favorito>> getFavoritosPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(favoritoService.findByUsuarioId(usuarioId));
    }

    @GetMapping("/favoritos/{usuarioId}")
    public ResponseEntity<List<Libro>> obtenerFavoritos(@PathVariable Long usuarioId) {
        List<Libro> favoritos = favoritoService.obtenerLibrosFavoritos(usuarioId);
        return ResponseEntity.ok(favoritos);
    }

    @PostMapping
    public ResponseEntity<Favorito> agregar(@RequestBody Favorito favorito) {
        return ResponseEntity.ok(favoritoService.save(favorito));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        favoritoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

