package com.stock.stock_simulator.service;

import com.stock.stock_simulator.entity.User;
import com.stock.stock_simulator.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User upsertUser(String gid, String nickname, String provider){
        User user = userRepository.findByGid(gid);
        if(user == null) user = new User();

        user.setGid(gid);
        user.setNickname(nickname);
        user.setProvider(provider);
        user.setWon(1000000000L);
        return userRepository.save(user);
    }
}
