package com.stock.stock_simulator.interfaces;

import com.stock.stock_simulator.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    public Stock findBySymbol(String symbol);
}
