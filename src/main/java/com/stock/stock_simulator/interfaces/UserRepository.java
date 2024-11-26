package com.stock.stock_simulator.interfaces;

import com.stock.stock_simulator.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    public User findById(String id);
    public User findByGid(String gid);
}
