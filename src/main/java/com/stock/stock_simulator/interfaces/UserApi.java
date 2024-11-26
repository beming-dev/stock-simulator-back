package com.stock.stock_simulator.interfaces;

import com.stock.stock_simulator.entity.Stock;
import com.stock.stock_simulator.entity.User;

import java.util.List;

public interface UserApi {
    public User getUserInfo(String token);
    public List<Stock> getUserHoldingStocks(String token);
    public void createUser(User user);
    public void updateUser(User user);
}
