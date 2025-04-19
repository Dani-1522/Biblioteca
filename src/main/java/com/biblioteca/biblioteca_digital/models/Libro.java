package com.biblioteca.biblioteca_digital.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "libros")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String autor;
    private String genero;
    private String descripcion;
    private int anioPublicacion;
    private String enlaceDescarga;

    // Relaciones
    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL)
    private List<Favorito> favoritos;

    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL)
    private List<Progreso> progresos;

    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL)
    private List<Calificacion> calificaciones;

    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL)
    private List<Resena> resenas;
}
