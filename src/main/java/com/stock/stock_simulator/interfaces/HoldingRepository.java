package com.stock.stock_simulator.interfaces;

import com.stock.stock_simulator.entity.Holding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoldingRepository extends JpaRepository<Holding, Long> {
    Holding findBySymbol(String symbol);
    Holding findBySymbolAndUserId(String symbol, String userId);
}
