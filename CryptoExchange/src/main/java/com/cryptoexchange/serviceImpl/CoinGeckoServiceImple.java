package com.cryptoexchange.serviceImpl;

import com.cryptoexchange.entity.Coin;
import com.cryptoexchange.repository.CoinRepository;
import com.cryptoexchange.service.CoinGeckoService;
import com.litesoftwares.coingecko.CoinGeckoApiClient;
import com.litesoftwares.coingecko.constant.Currency;
import com.litesoftwares.coingecko.domain.Coins.CoinMarkets;
import com.litesoftwares.coingecko.impl.CoinGeckoApiClientImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class CoinGeckoServiceImple implements CoinGeckoService {

    @Autowired
    private CoinRepository coinRepository;

    @Scheduled(initialDelayString = ("${schedule.updatePrice.init}"), fixedDelayString = "${schedule.updatePrice.work}")
    public void priceUpdate() {

        //при условии, что в БД есть монеты
        if (coinRepository.getCount() > 0) {

            CoinGeckoApiClient client = new CoinGeckoApiClientImpl();

            //количество страниц
            for (int i = 0; i < 11; i++) {

                List<CoinMarkets> coinMarkets = client.getCoinMarkets(Currency.USD, "", "", 200, i, true, "");
                List<Coin> allCoinList = coinRepository.getAllCoinList();

                updatePriceInBD(coinMarkets, allCoinList);
            }

            client.shutdown();
        }
    }

    public void updatePriceInBD(List<CoinMarkets> coinMarkets, List<Coin> allCoinList) {

        for (Coin coin : allCoinList) {

            for (CoinMarkets coinMarket : coinMarkets) {
                if (coin.getCoinId().equalsIgnoreCase(coinMarket.getId())) {

                    coinRepository.updateFieldsWithPrice(coin.getCoinId()
                            , coinMarket.getMarketCapRank()
                            , coinMarket.getCurrentPrice()
                            , formattedTime(coinMarket.getAthDate())
                            , coinMarket.getAth()
                            , coinMarket.getHigh24h()
                            , coinMarket.getLow24h()
                            , coinMarket.getPriceChange24h()
                            , coinMarket.getPriceChangePercentage24h()
                    );
                    break;
                }
            }
        }
    }

    @Override
    public String formattedTime(String date) {
//        String date = "2011-08-11T01:23:45.678Z";

        DateTimeFormatter dft2 = DateTimeFormatter
                .ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        LocalDateTime localDateTime = LocalDateTime.parse(date, dft2);

        return DateTimeFormatter.ofPattern("yyyy/MM/dd - HH:mm:ss").format(localDateTime);

    }
}