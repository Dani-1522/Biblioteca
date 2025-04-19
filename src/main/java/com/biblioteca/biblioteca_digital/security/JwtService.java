package com.biblioteca.biblioteca_digital.security;

import com.biblioteca.biblioteca_digital.models.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    private static final String SECRET_KEY = "mi-clave-secreta-muy-larga-y-segura-para-jwt-en-produccion"; // >= 256 bits

    public String generateToken(Usuario usuario) {
        return Jwts.builder()
                .setSubject(usuario.getUsername())
                .claim("rol", usuario.getRol())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24h
                .signWith(getKey())
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(getKey()).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        return extractUsername(token).equals(userDetails.getUsername()) &&
                !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parserBuilder().setSigningKey(getKey()).build()
                .parseClaimsJws(token).getBody().getExpiration();
        return expiration.before(new Date());
    }

    private Key getKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }
}

