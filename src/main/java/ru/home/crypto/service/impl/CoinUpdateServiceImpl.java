package ru.home.crypto.service.impl;

import com.litesoftwares.coingecko.CoinGeckoApiClient;
import com.litesoftwares.coingecko.constant.Currency;
import com.litesoftwares.coingecko.domain.Coins.CoinMarkets;
import com.litesoftwares.coingecko.domain.Ping;
import com.litesoftwares.coingecko.impl.CoinGeckoApiClientImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.home.crypto.entity.Coin;
import ru.home.crypto.mapper.CoinMapper;
import ru.home.crypto.repository.CoinRepository;
import ru.home.crypto.service.CoinUpdateService;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CoinUpdateServiceImpl implements CoinUpdateService {

    private final CoinRepository coinRepository;
    private final CoinMapper coinMapper;

    public void updateCoins() {
        CoinGeckoApiClient coinGeckoApiClient = new CoinGeckoApiClientImpl();
        Ping ping = coinGeckoApiClient.ping();
        coinGeckoApiClient.shutdown();
        if (ping.getGeckoSays().equals("(V3) To the Moon!")) {
            priceUpdate(coinGeckoApiClient);
        }
        coinGeckoApiClient.shutdown();
    }


    private void priceUpdate(CoinGeckoApiClient coinGeckoApiClient) {

        //количество страниц
        for (int i = 0; i < 26; i++) {

            List<CoinMarkets> coinMarkets = coinGeckoApiClient.getCoinMarkets(Currency.USD, "", "", 200, i, false, "");

            updatePriceInBD(coinMarkets);
        }
    }

    private void updatePriceInBD(List<CoinMarkets> coinMarkets) {

        List<Coin> coins = new ArrayList<>();

        for (CoinMarkets coinMarket : coinMarkets) {
            coins.add(coinMapper.coinMarketsToCoin(coinMarket));

            System.out.println();
        }
    }

//    private String formattedTime(String date) {
//        String date = "2011-08-11T01:23:45.678Z";

//        DateTimeFormatter dft2 = DateTimeFormatter
//                .ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
//        LocalDateTime localDateTime = LocalDateTime.parse(date, dft2);
//
//        return DateTimeFormatter.ofPattern("yyyy/MM/dd - HH:mm:ss").format(localDateTime);
//
// Преобразование строки в LocalDateTime
//        LocalDateTime dateTime = LocalDateTime.parse(input, DateTimeFormatter.ISO_OFFSET_DATE_TIME);

// Получение даты в нужном формате
//        String formattedDate = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

//        System.out.println(formattedDate); // Выводит: 2021-11-10
//    }
}