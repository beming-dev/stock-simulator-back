package com.stock.stock_simulator.entity;

public class Stock {
    private String id;
    //    티커
    private String ticker;
    //    티커
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id='" + id + '\'' +
                ", ticker='" + ticker + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
