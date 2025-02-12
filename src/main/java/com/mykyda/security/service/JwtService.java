package com.mykyda.security.service;

import com.mykyda.security.database.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
@RequiredArgsConstructor
public class JwtService {
    @Value("${spring.security.jwt.secret}")
    private String SECRET_KEY;

    private final UserService userService;

    public String getSecretKey(){
        return SECRET_KEY;
    }
    public String generateToken(User userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateToken(String email) {
        var user = userService.findByEmail(email);
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parseClaimsJws(token).getBody();
    }

    public java.util.Date getExpirationDateFromToken(String token) {
        return getAllClaimsFromToken(token).getExpiration();
    }

    private Boolean isTokenExpired(String token) {
        final java.util.Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new java.util.Date());
    }

    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }
}
