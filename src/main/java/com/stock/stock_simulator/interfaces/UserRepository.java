package com.stock.stock_simulator.interfaces;

import com.stock.stock_simulator.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByGid(String gid);
}
