package com.stock.stock_simulator.entity;

import jakarta.persistence.*;

@Entity
public class Token {
    @Id
    private String type;

    @Column(length = 1024) // 1024자로 제한
    private String token;
    private String expires;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String key) {
        this.token = key;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    @Override
    public String toString() {
        return "Key{" +
                ", key='" + token + '\'' +
                ", expires='" + expires + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
