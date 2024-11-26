package com.stock.stock_simulator.service;

import com.stock.stock_simulator.entity.User;
import com.stock.stock_simulator.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserInfo(String id){
        return this.userRepository.findById(id);
    }
}
