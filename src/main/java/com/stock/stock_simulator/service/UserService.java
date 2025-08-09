package com.stock.stock_simulator.service;

import com.stock.stock_simulator.entity.User;
import com.stock.stock_simulator.interfaces.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final HttpServletRequest request;

    @Autowired
    public UserService(
            UserRepository userRepository,
            HttpServletRequest request)
    {
        this.userRepository = userRepository;
        this.request = request;
    }

    @Transactional
    public User upsertUser(String gid, String nickname, String provider){
        User user = userRepository.findByGid(gid);
        if(user == null) user = new User();

        user.updateDefaultUser(gid, nickname, provider);

        return userRepository.save(user);
    }

    public User getUser(){
        String gid = this.request.getAttribute("gid").toString();

        System.out.println(userRepository.findByGid(gid));

        return userRepository.findByGid(gid);
    }
}
