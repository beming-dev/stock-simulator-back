package com.stock.stock_simulator.config;

import com.stock.stock_simulator.entity.Stock;
import com.stock.stock_simulator.interfaces.StockRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(StockRepository stockRepository) {
        return args -> {
            // 예시 데이터 삽입
            Stock stock1 = new Stock();
            stock1.setSymbol("AAPL");
            stock1.setName("Apple Inc.");
            stock1.setType("J");
            stock1.setCountry("NAS");

            Stock stock2 = new Stock();
            stock2.setSymbol("GOOGL");
            stock2.setName("Google LLC");
            stock2.setType("J");
            stock2.setCountry("NAS");

            Stock stock3 = new Stock();
            stock3.setSymbol("005930");
            stock3.setName("Samsung Electronics");
            stock3.setType("J");
            stock3.setCountry("KOS");

            // 저장
            stockRepository.save(stock1);
            stockRepository.save(stock2);
            stockRepository.save(stock3);

            System.out.println("Example data initialized in H2 database.");
        };
    }
}