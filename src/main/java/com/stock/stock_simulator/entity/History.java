package com.stock.stock_simulator.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String userId;
    public String symbol;

    //type: sell or buy
    public String type;
    public Integer amount;
    public Double price;

    public String timestamp;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String ticker) {
        this.symbol = ticker;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void handleBuy(String gid, String symbol, Integer amount, Double price){
        this.symbol = symbol;
        this.userId = gid;
        this.amount = amount;
        this.price = price;
        this.timestamp = new Date().toString();
        this.type = "buy";
    }

    public void handleSell(String gid, String symbol, Integer amount, Double price){
        this.symbol = symbol;
        this.userId = gid;
        this.amount = amount;
        this.price = price;
        this.timestamp = new Date().toString();
        this.type = "sell";
    }

    @Override
    public String toString() {
        return "History{" +
                "userId='" + userId + '\'' +
                ", ticker='" + symbol + '\'' +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
