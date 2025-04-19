package com.biblioteca.biblioteca_digital.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank
    private String nombre;

    @Email
    @NotBlank
    private String correo;

    @NotBlank
    private String username;

    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;

}