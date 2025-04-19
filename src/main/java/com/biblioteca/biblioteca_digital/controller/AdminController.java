package com.biblioteca.biblioteca_digital.controller;

import com.biblioteca.biblioteca_digital.dto.RegisterRequest;
import com.biblioteca.biblioteca_digital.models.Libro;
import com.biblioteca.biblioteca_digital.models.Rol;
import com.biblioteca.biblioteca_digital.models.Usuario;
import com.biblioteca.biblioteca_digital.repository.LibroRepository;
import com.biblioteca.biblioteca_digital.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final UsuarioRepository usuarioRepo;
    private final LibroRepository libroRepo;
    private final PasswordEncoder passwordEncoder;

    public AdminController(UsuarioRepository usuarioRepo, LibroRepository libroRepo, PasswordEncoder passwordEncoder) {
        this.usuarioRepo = usuarioRepo;
        this.libroRepo = libroRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/usuarios/admin")
    public ResponseEntity<Usuario> registrarAdmin(@RequestBody RegisterRequest request) {
        if (usuarioRepo.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Ya existe un usuario con ese username");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setCorreo(request.getCorreo());
        usuario.setUsername(request.getUsername());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setRol(Rol.ADMIN);
        usuarioRepo.save(usuario);

        return ResponseEntity.ok(usuario);
    }


    // Usuarios
    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(usuarioRepo.findAll());
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Libros
    @PostMapping("/libros")
    public ResponseEntity<Libro> crearLibro(@RequestBody Libro libro) {
        return ResponseEntity.ok(libroRepo.save(libro));
    }

    @PutMapping("/libros/{id}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable Long id, @RequestBody Libro libro) {
        libro.setId(id);
        return ResponseEntity.ok(libroRepo.save(libro));
    }

    @DeleteMapping("/libros/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable Long id) {
        libroRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

