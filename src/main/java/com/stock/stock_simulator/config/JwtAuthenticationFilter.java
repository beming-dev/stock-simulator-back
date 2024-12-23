package com.stock.stock_simulator.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final Key secretKey;

    public JwtAuthenticationFilter(@Value("${jwt.secret}") String keyString) {
        this.secretKey = Keys.hmacShaKeyFor(keyString.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        String path = request.getRequestURI();
        // GET 요청은 토큰 없이도 허용
        if ("GET".equalsIgnoreCase(request.getMethod()) || path.startsWith("/h2-console") || path.startsWith("/ws")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Authorization 헤더가 없는 경우
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Missing or invalid Authorization header");
            return;
        }

        String token = authHeader.substring(7); // "Bearer " 제거

        try {
            // JWT 검증
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            // 만료 시간(exp) 및 발급 시간(iat) 검증
            Date now = new Date();
            if (claims.getExpiration().before(now)) {
                throw new IllegalArgumentException("Token has expired");
            }
            if (claims.getIssuedAt().after(now)) {
                throw new IllegalArgumentException("Token issued in the future");
            }


            // 사용자 정보를 요청 속성에 추가
            request.setAttribute("gid", claims.getSubject()); // 예: userId를 저장
            request.setAttribute("roles", claims.get("roles")); // 역할 정보 저장 가능

        } catch (IllegalArgumentException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized");
            return;
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized");
            return;
        }

        // 다음 필터로 진행
        filterChain.doFilter(request, response);
    }
}