package com.stock.stock_simulator.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Holding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String userId;
    public String symbol;
    public Double buyPrice;

    //평균단가
    public Double average = (double) 0;
    public Integer amount = 0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

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

    public Double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }


    public void handleBuy(String gid, String symbol, Integer amount, Double price){
        double rawAvg = ((this.average * this.amount) + (amount * price)) / (this.amount + amount);
        double newAvg = Math.round(rawAvg * 100.0) / 100.0;

        this.symbol = symbol;
        this.buyPrice = price;
        this.average = newAvg;
        this.amount += amount;
        this.userId = gid;
    }

    public void handleSell(Integer amount){
        this.amount = Math.max(amount, 0);
    }

    @Override
    public String toString() {
        return "Holding{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", symbol='" + symbol + '\'' +
                ", buyPrice='" + buyPrice + '\'' +
                ", average='" + average + '\'' +
                ", amount=" + amount +
                '}';
    }
}
