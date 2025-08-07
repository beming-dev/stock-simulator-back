package com.stock.stock_simulator.DTO;

public class HoldingDto {
    public HoldingDto(
            Long id,
            Integer amount,
            Double average,
            Double buyPrice,
            String symbol,
            String stockName
    ) {
        this.id = id;
        this.symbol = symbol;
        this.stockName = stockName;
        this.amount = amount;
        this.average = average;
        this.buyPrice = buyPrice;
    }

    private Long   id;
    private String symbol;
    private String stockName;
    private Integer amount;
    private Double average;
    private Double buyPrice;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public Double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }
}
