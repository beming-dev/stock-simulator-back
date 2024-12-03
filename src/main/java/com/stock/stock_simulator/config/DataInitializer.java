package com.stock.stock_simulator.config;

import com.stock.stock_simulator.entity.Stock;
import com.stock.stock_simulator.entity.Token;
import com.stock.stock_simulator.interfaces.StockRepository;
import com.stock.stock_simulator.interfaces.TokenRepository;
import com.stock.stock_simulator.service.CrawlingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner initDatabase(TokenRepository tokenRepository, CrawlingService crawlingService) {
        crawlingService.fetchNewYorkSymbolData();
        crawlingService.fetchAmexSymbolData();
        crawlingService.fetchNasdaqSymbolData();
        return args -> {
            // 예시 데이터 삽입

            Token token = new Token();
            token.setToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0b2tlbiIsImF1ZCI6ImIwYjU1M2NjLTY4ZWYtNDRlYy1iNmI5LTkxMDAyYTRhZmQyYyIsInByZHRfY2QiOiIiLCJpc3MiOiJ1bm9ndyIsImV4cCI6MTczMzAxODgyMiwiaWF0IjoxNzMyOTMyNDIyLCJqdGkiOiJQU3NTeDJ5MjBZR2tlMzNpeVluZ3A0aHJyc3czU1cxVHdCVE4ifQ.vfdppFQF9vF6V3b7dEfI5d46Te4och4hF3Mh1aLXkL0Jv8_JH6o8T2e9XseO2RvXPq6b1hVIvqg8oCVkSZp5Yg");
            token.setExpires("2024-12-01 11:07:02");
            token.setType("access_token");
            tokenRepository.save(token);

            System.out.println("Example data initialized in H2 database.");
        };
    }
}