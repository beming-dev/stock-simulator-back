package com.stock.stock_simulator.interfaces;

import com.stock.stock_simulator.entity.Holding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoldingRepository extends JpaRepository<Holding, Long> {
    List<Holding> findByUserId(String userId);
    Holding findBySymbol(String symbol);
    Holding findBySymbolAndUserId(String symbol, String userId);
}
