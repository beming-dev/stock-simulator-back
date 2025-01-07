package com.stock.stock_simulator.interfaces;

import com.stock.stock_simulator.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Stock, Long> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO likes (user_id, symbol) VALUES (:userGid, :symbol)", nativeQuery = true)
    void insertLike(@Param("userGid") String userGid, @Param("symbol") String symbol);
}
