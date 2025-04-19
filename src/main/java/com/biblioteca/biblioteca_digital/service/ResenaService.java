package com.biblioteca.biblioteca_digital.service;
import com.biblioteca.biblioteca_digital.models.Resena;
import com.biblioteca.biblioteca_digital.repository.ResenaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResenaService {

    private final ResenaRepository repo;

    public ResenaService(ResenaRepository repo) {
        this.repo = repo;
    }

    public List<Resena> obtenerResenasDeLibro(Long libroId) {
        return repo.findByLibroId(libroId);
    }

    public List<Resena> findByLibroId(Long libroId) {
        return repo.findByLibroId(libroId);
    }

    public Resena save(Resena resena) {
        return repo.save(resena);
    }
}

