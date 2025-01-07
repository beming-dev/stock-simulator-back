package com.stock.stock_simulator.entity;

import jakarta.persistence.*;

@Entity
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName = "gid", nullable = false)
    public User userId;

    @ManyToOne
    @JoinColumn(name="symbol", referencedColumnName = "symbol", nullable = false)
    public Stock symbol;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Stock getSymbol() {
        return symbol;
    }

    public void setSymbol(Stock symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "Like{" +
                "id=" + id +
                ", userId=" + userId +
                ", symbol=" + symbol +
                '}';
    }
}
