package com.stock.stock_simulator.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String gid;
    private Long won;
    private String nickname;
    private String provider;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gId) {
        this.gid = gId;
    }

    public Long getWon() {
        return won;
    }

    public void setWon(Long won) {
        this.won = won;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", gId='" + gid + '\'' +
                ", won='" + won + '\'' +
                ", nickname='" + nickname + '\'' +
                ", provider='" + provider + '\'' +
                '}';
    }
}
