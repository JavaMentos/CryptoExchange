package com.cryptoexchange.service;

import com.cryptoexchange.entity.Coin;
import com.litesoftwares.coingecko.domain.Coins.CoinMarkets;

import java.util.List;

public interface CoinGeckoService{
    String formattedTime(String date);
    void updatePriceInBD(List<CoinMarkets> coinMarkets, List<Coin> allCoinList);
}
