package com.stock.stock_simulator.entity;

public class Holding {
    public String userId;
    public String ticker;
    public String buyPrice;
    //평균단가
    public String average;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(String buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    @Override
    public String toString() {
        return "Holding{" +
                "userId='" + userId + '\'' +
                ", ticker='" + ticker + '\'' +
                ", buyPrice='" + buyPrice + '\'' +
                ", average='" + average + '\'' +
                '}';
    }
}
