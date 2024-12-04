package com.stock.stock_simulator.interfaces;

import com.stock.stock_simulator.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    public Optional<Stock> findBySymbol(String symbol);
}
