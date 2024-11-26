package com.stock.stock_simulator.entity;

public class User {
    private String id;
    private String gId;
    private String won;
    private String nickname;
    private String provider;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getgId() {
        return gId;
    }

    public void setgId(String gId) {
        this.gId = gId;
    }

    public String getWon() {
        return won;
    }

    public void setWon(String won) {
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
                ", gId='" + gId + '\'' +
                ", won='" + won + '\'' +
                ", nickname='" + nickname + '\'' +
                ", provider='" + provider + '\'' +
                '}';
    }
}
