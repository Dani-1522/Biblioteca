package com.biblioteca.biblioteca_digital.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(unique = true)
    private String correo;

    @Column(unique = true)
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Rol rol;



    // Relaciones
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Favorito> favoritos;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Progreso> progresos;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Calificacion> calificaciones;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Resena> resenas;
}
