package com.stock.stock_simulator.DTO;

public class StockOrderDto {
    private String symbol;
    private Integer amount;

    // Getter/Setter
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}