package ru.home.crypto.service.impl;

import com.litesoftwares.coingecko.CoinGeckoApiClient;
import com.litesoftwares.coingecko.constant.Currency;
import com.litesoftwares.coingecko.domain.Coins.CoinMarkets;
import com.litesoftwares.coingecko.domain.Ping;
import com.litesoftwares.coingecko.impl.CoinGeckoApiClientImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.home.crypto.entity.Coin;
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

    public void updateCoins() {
        CoinGeckoApiClient coinGeckoApiClient = new CoinGeckoApiClientImpl();
        Ping ping = coinGeckoApiClient.ping();
        coinGeckoApiClient.shutdown();
        if (ping.getGeckoSays().equals("(V3) To the Moon!")) {
            prepareCoinsAndSave(coinGeckoApiClient);
        }
        coinGeckoApiClient.shutdown();
    }


    private void prepareCoinsAndSave(CoinGeckoApiClient coinGeckoApiClient) {
        List<Coin> coins = new ArrayList<>();

        //количество страниц, берем 1000 монет
        for (int i = 0; i < 5; i++) {
            List<CoinMarkets> coinMarkets = coinGeckoApiClient
                    .getCoinMarkets(Currency.USD, "", "", 200, i, false, "");

            coins.addAll(coinMarkets.stream()
                    .map(coinMapper::coinMarketsToCoin)
                    .toList());

            sleep();
        }
        saveAndUpdateCoins(coins);
    }

    private void saveAndUpdateCoins(List<Coin> coins) {

        List<Coin> uniqCoins = coins.stream()
                .collect(Collectors.toMap(Coin::getCoinId, Function.identity(), (oldValue, newValue) -> newValue))
                .values().stream().toList();

        coinRepository.saveAllAndFlush(uniqCoins);
    }

    private void sleep() {
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}