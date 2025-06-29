package apontamento.apontaai.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;


import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private static final long EXPIRATION_MS = 1000 * 60 * 60 * 24; // 24 horas
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512); // Chave aleatória (memória)

    public String generateToken(String email, Long userId) {
        return Jwts.builder()
                .setSubject(email)
                .claim("uid", userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(key)
                .compact();
    }

    public boolean isTokenValid(String token) {
        try {
            parse(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String extractEmail(String token) {
        return parse(token).getBody().getSubject();
    }

    public Long extractUserId(String token) {
        return parse(token).getBody().get("uid", Long.class);
    }

    private Jws<Claims> parse(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }
}
