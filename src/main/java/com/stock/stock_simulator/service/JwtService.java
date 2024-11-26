package com.stock.stock_simulator.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private final Key secretKey;

    // Secret key 생성 (강력한 키를 사용해야 함)
    public JwtService() {
        this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public String generateToken(String userId, String email, String name) {
        return Jwts.builder()
                .setSubject(userId) // 사용자 고유 식별자
                .claim("email", email) // 추가 클레임
                .claim("name", name) // 추가 클레임
                .setIssuer("your-app-name") // 발급자
                .setIssuedAt(new Date()) // 토큰 발급 시간
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 만료 시간 (1시간)
                .signWith(secretKey) // 비밀 키로 서명
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false; // 토큰이 유효하지 않음
        }
    }
}