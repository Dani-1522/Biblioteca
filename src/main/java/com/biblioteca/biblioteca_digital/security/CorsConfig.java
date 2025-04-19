package com.biblioteca.biblioteca_digital.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Aplica la configuración a todas las rutas bajo /api/
                .allowedOrigins("http://127.0.0.1:5500") // Reemplaza con el origen de tu frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true); // Si necesitas enviar cookies o información de autenticación
    }
}