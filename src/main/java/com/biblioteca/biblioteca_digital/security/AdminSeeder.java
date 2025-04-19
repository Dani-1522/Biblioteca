package com.biblioteca.biblioteca_digital.security;

import com.biblioteca.biblioteca_digital.models.Rol;
import com.biblioteca.biblioteca_digital.models.Usuario;
import com.biblioteca.biblioteca_digital.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminSeeder implements CommandLineRunner {

    private final UsuarioRepository repo;
    private  final PasswordEncoder encoder;

    public AdminSeeder(UsuarioRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }
    public void run(String... args) throws Exception {
        if (!repo.existsByUsername("admin")) {
            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setCorreo("admin@biblioteca.com");
            admin.setPassword(encoder.encode("admin123"));
            admin.setRol(Rol.ADMIN);
            repo.save(admin);
            System.out.println("Se creó el admin por defecto: admin / admin123");
        } else {
            System.out.println(" Admin ya existe, no se creó uno nuevo.");
        }
    }

}
