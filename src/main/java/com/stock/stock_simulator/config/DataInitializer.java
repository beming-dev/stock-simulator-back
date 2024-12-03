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
        crawlingService.downloadKosdaqCsvWithOtp();
        crawlingService.downloadKospiCsvWithOtp();
        return args -> {
            // 예시 데이터 삽입

            Token token = new Token();
            token.setToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0b2tlbiIsImF1ZCI6ImQ4ZDVlZDUzLTY2ZGItNDMxMy1iN2IyLTEwZWQxMGRhZGRkMSIsInByZHRfY2QiOiIiLCJpc3MiOiJ1bm9ndyIsImV4cCI6MTczMzI3ODE5MCwiaWF0IjoxNzMzMTkxNzkwLCJqdGkiOiJQU3NTeDJ5MjBZR2tlMzNpeVluZ3A0aHJyc3czU1cxVHdCVE4ifQ.BKVmEdAb3O3J1wbK6BpDbLrwucH3CzLFDITiG3ddpi6Qi0X05WfAkI-LTvCOE7T19b1vn7iusDAabbao0K0QRQ");
            token.setExpires("2024-12-04 11:07:02");
            token.setType("access_token");
            tokenRepository.save(token);

            System.out.println("Example data initialized in H2 database.");
        };
    }
}