package com.platform.onlinecourse.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Slf4j
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpirationMillis;

    private Key getSigningKey() {
        log.trace("Retrieving signing key for JWT");
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public String generateToken(UserDetails userDetails) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + jwtExpirationMillis);

        log.debug("Generating JWT for user: {}", userDetails.getUsername());
        log.trace("Token expiration set to: {}", expiry);

        String token = Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("role", userDetails.getAuthorities())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();

        log.info("JWT token generated successfully for user: {}", userDetails.getUsername());
        return token;
    }

    public String extractUsername(String token) {
        String username = getClaims(token).getSubject();
        log.debug("Extracted username from token: {}", username);
        return username;
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
