package com.biblioteca.biblioteca_digital.controller;


import com.biblioteca.biblioteca_digital.dto.*;
import com.biblioteca.biblioteca_digital.models.Rol;
import com.biblioteca.biblioteca_digital.models.Usuario;
import com.biblioteca.biblioteca_digital.repository.UsuarioRepository;
import com.biblioteca.biblioteca_digital.security.JwtService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final UsuarioRepository usuarioRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authManager, UsuarioRepository usuarioRepo,
                          PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.authManager = authManager;
        this.usuarioRepo = usuarioRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        if (usuarioRepo.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest().body(new AuthResponse("Nombre de usuario ya existe"));
        }
        if (usuarioRepo.existsByCorreo(request.getCorreo())) {
            return ResponseEntity.badRequest().body(new AuthResponse("Correo ya registrado"));
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setCorreo(request.getCorreo());
        usuario.setUsername(request.getUsername());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setRol(Rol.USER);
        usuarioRepo.save(usuario);



        String token = jwtService.generateToken(usuario);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            Usuario usuario = usuarioRepo.findByUsername(request.getUsername())
                    .orElseThrow();

            if (usuario.getRol() != Rol.ADMIN && usuario.getRol() != Rol.USER) {
                return ResponseEntity.status(403).body(new AuthResponse("No autorizado (rol incorrecto)"));
            }

            String token = jwtService.generateToken(usuario);
            return ResponseEntity.ok(new AuthResponse(token));

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body(new AuthResponse("Credenciales incorrectas"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new AuthResponse("Error interno: " + e.getMessage()));
        }
    }


    @GetMapping("/me")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<String> me(Authentication auth) {
        return ResponseEntity.ok("Usuario autenticado: " + auth.getName());
    }
}
