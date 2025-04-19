package com.biblioteca.biblioteca_digital.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;

}