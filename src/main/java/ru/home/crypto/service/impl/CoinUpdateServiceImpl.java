package ru.home.crypto.service.impl;

import com.litesoftwares.coingecko.constant.Currency;
import com.litesoftwares.coingecko.domain.Coins.CoinMarkets;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.home.crypto.entity.Coin;
import ru.home.crypto.external.gecko.CoinGeckoApiClientWrapper;
import ru.home.crypto.mapper.CoinMapper;
import ru.home.crypto.repository.CoinRepository;
import ru.home.crypto.service.CoinUpdateService;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CoinUpdateServiceImpl implements CoinUpdateService {

    private final CoinRepository coinRepository;
    private final CoinMapper coinMapper;
    private final CoinGeckoApiClientWrapper coinGeckoApiClient;

    public void updateCoins() {
        if (coinGeckoApiClient.isAvailable()) prepareCoinsAndSave();
        coinGeckoApiClient.shootDawn();
    }


    private void prepareCoinsAndSave() {
        List<Coin> coins = new ArrayList<>();
        String usd = Currency.USD;
        coinGeckoApiClient.start();
        //количество страниц, берем 1000 монет
        for (int i = 0; i < 5; i++) {
            List<CoinMarkets> coinMarkets = coinGeckoApiClient.getCoinMarkets(usd, 200, i);

            coins.addAll(coinMarkets.stream()
                    .map(coinMapper::coinMarketsToCoin)
                    .toList());

            sleep();
        }
        coinGeckoApiClient.shootDawn();
        saveAndUpdateCoins(coins);
    }

    private void saveAndUpdateCoins(List<Coin> coins) {

        List<Coin> uniqCoins = coins.stream()
                .collect(Collectors.toMap(Coin::getCoinId, Function.identity(), (oldValue, newValue) -> newValue))
                .values().stream().toList();

        coinRepository.saveAll(uniqCoins);
    }

    private void sleep() {
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}