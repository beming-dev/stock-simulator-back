package com.stock.stock_simulator.entity;

public class History {
    public String userId;
    public String ticker;

    //type: sell or buy
    public String type;
    public Integer amount;
    public Integer price;

    public String timestamp;

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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "History{" +
                "userId='" + userId + '\'' +
                ", ticker='" + ticker + '\'' +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
