package com.stock.stock_simulator.interfaces;

import com.stock.stock_simulator.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeyRepository extends JpaRepository<Token, Integer> {
    Token findByType(String type);
}
