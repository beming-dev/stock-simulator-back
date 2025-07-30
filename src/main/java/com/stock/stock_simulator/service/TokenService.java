package com.stock.stock_simulator.service;

import com.stock.stock_simulator.entity.Token;
import com.stock.stock_simulator.interfaces.TokenRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class TokenService {
    private TokenRepository tokenRepository;

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }


    public static boolean isTokenExpired(String expiredTimeStr) {
        // 1. 날짜 형식 지정
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // 2. 만료 시간 문자열을 LocalDateTime으로 변환
        LocalDateTime expiredLocal = LocalDateTime.parse(expiredTimeStr, formatter);

        // 3. 한국 시간대(Asia/Seoul) 지정
        ZoneId seoulZone = ZoneId.of("Asia/Seoul");
        // 4. 만료 시간에 한국 시간대 정보 붙이기
        ZonedDateTime expiredSeoul = expiredLocal.atZone(seoulZone);
        // 5. 현재 시간(한국 시간) 가져오기
        ZonedDateTime nowSeoul = ZonedDateTime.now(seoulZone);

        // 6. 만료 여부 확인
        return nowSeoul.isAfter(expiredSeoul);
    }

    public Token getAccessToken(){
        Token token = tokenRepository.findByType("access_token");
        return token;
    }

    public void createAccessToken(Token token){
        tokenRepository.save(token);
        return;
    }
}
