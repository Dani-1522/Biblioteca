package com.biblioteca.biblioteca_digital.models;


import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String role; // ADMIN o USER

    @ElementCollection
    private List<Long> favoriteBooks;
}
