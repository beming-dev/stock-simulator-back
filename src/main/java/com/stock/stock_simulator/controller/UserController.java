package com.stock.stock_simulator.controller;

import com.stock.stock_simulator.entity.User;
import com.stock.stock_simulator.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public User getUser(@RequestParam String id){
        return this.userService.getUserInfo(id);
    }

    @PostMapping("/")
    public void RegisterUser(@RequestBody User user){
    }
}
