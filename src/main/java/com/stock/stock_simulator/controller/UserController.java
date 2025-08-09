package com.stock.stock_simulator.controller;

import com.stock.stock_simulator.entity.User;
import com.stock.stock_simulator.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("info")
    public User info() {
        return this.userService.getUser();
    }
}
