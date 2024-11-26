package com.stock.stock_simulator.infra;

import com.stock.stock_simulator.entity.User;
import com.stock.stock_simulator.interfaces.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class MysqlUserRepository implements UserRepository {
    @Override
    public User findById(String id) {
        return null;
    }

    @Override
    public User findByGid(String gid) {
        return null;
    }
}
