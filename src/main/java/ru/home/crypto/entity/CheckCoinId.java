package ru.home.crypto.entity;

import org.springframework.stereotype.Component;

@Component
public class CheckCoinId {
    private String coinId;

    public String getCoinId() {
        return coinId;
    }

    public void setCoinId(String coinId) {
        this.coinId = coinId;
    }

}
