package com.platform.onlinecourse.utils;

import com.platform.onlinecourse.model.AppUser;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Duration;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    private static final long EXPIRATION = 1000 * 60 * 60;

    private Key getSigningKey() {
        log.trace("Retrieving signing key for JWT");
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public String generateToken(AppUser appUser) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + EXPIRATION);

        log.debug("Generating JWT for user: {}", appUser.getUsername());
        log.trace("Token expiration set to: {}", expiry);

        String token = Jwts.builder()
                .setSubject(appUser.getUsername())
                .claim("role", appUser.getRole())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();

        log.info("JWT token generated successfully for user: {}", appUser.getUsername());
        return token;
    }

    public String extractUsername(String token) {
        String username = getClaims(token).getSubject();
        log.debug("Extracted username from token: {}", username);
        return username;
    }

    public String extractRole(String token) {
        String role = getClaims(token).get("role", String.class);
        log.debug("Extracted role from token: {}", role);
        return role;
    }

    public boolean isTokenValid(String token) {
        try {
            getClaims(token);
            log.trace("Token is valid");
            return true;
        } catch (JwtException e) {
            log.warn("Invalid JWT token: {}", e.getMessage());
            return false;
        }
    }

    public boolean isTokenExpired(String token) {
        try {
            boolean expired = getClaims(token).getExpiration().before(new Date());
            log.debug("Token expired: {}", expired);
            return expired;
        } catch (JwtException e) {
            log.error("Could not check token expiration: {}", e.getMessage());
            return true;
        }
    }

    private Claims getClaims(String token) {
        log.trace("Parsing JWT token to extract claims");
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
