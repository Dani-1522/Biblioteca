package com.biblioteca.biblioteca_digital.service;
import com.biblioteca.biblioteca_digital.models.Favorito;
import com.biblioteca.biblioteca_digital.models.Libro;
import com.biblioteca.biblioteca_digital.repository.FavoritoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoritoService {

    private final FavoritoRepository repo;

    public FavoritoService(FavoritoRepository repo) {
        this.repo = repo;
    }

    public List<Favorito> findByUsuarioId(Long usuarioId) {
        return repo.findByUsuarioId(usuarioId);
    }

    public List<Libro> obtenerLibrosFavoritos(Long usuarioId) {
        List<Favorito> favoritos = repo.findByUsuarioId(usuarioId);
        return favoritos.stream()
                .map(Favorito::getLibro)
                .collect(Collectors.toList());
    }

    public Favorito save(Favorito favorito) {
        return repo.save(favorito);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}

