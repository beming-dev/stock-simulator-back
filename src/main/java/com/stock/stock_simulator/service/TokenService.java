package com.stock.stock_simulator.service;

import com.stock.stock_simulator.entity.Token;
import com.stock.stock_simulator.interfaces.KeyRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class TokenService {
    private KeyRepository keyRepository;

    public TokenService(KeyRepository keyRepository) {
        this.keyRepository = keyRepository;
    }


    public static boolean isTokenExpired(String expiredTimeStr) {
        // 1. 날짜 형식 지정 (받은 JSON의 날짜 형식에 맞춰야 함)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 2. 만료 시간 문자열을 LocalDateTime으로 변환
        LocalDateTime expiredTime = LocalDateTime.parse(expiredTimeStr, formatter);

        // 3. 현재 시간 가져오기
        LocalDateTime now = LocalDateTime.now();

        // 4. 만료 여부 확인
        return now.isAfter(expiredTime); // 현재 시간이 만료 시간 이후인지 확인
    }

    public Token getAccessToken(){
        Token token = keyRepository.findByType("access_token");
        return token;
    }

    public void createAccessToken(Token token){
        keyRepository.save(token);
        return;
    }
}
