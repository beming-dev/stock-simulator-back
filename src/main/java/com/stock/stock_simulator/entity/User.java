package com.stock.stock_simulator.entity;

import com.stock.stock_simulator.constant.StockConstant;
import com.stock.stock_simulator.utils.StockUtil;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Users", indexes = {
        @Index(name="idx_gid", columnList = "gid")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String gid;
    private Long won;
    private Long dollars;
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

    public Long getDollars() {
        return won;
    }
    public void setDollars(Long dollars) {
        this.dollars = dollars;
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

    public void updateDefaultUser(String gid, String nickname, String provider){
        this.setGid(gid);
        this.setNickname(nickname);
        this.setProvider(provider);
        this.setWon(1000000000L);
    }

    public void updateAsset(String country, Double price){
        if(Objects.equals(country, StockConstant.KO)) setWon((long) (this.won + price));
        else setDollars((long) (this.dollars - price));
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
