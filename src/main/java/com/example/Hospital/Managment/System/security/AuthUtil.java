package com.example.Hospital.Managment.System.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.Hospital.Managment.System.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


@Component
public class AuthUtil {

    @Value("${jwt.secretKey}")
    private String jwtSecretKey;
    
    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }

    @SuppressWarnings("deprecation")
    public String generaterAccessToken(User user){
        return Jwts.builder()
            .setSubject(user.getUsername())
            .claim("userId", user.getId().toString())
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis()+10*60*1000))
            .signWith(getSecretKey())
            .compact();
    }

        public String getUsernameFromToken(String token) {
        Claims claims =  Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }
    
}
