package ru.home.crypto.external.gecko;


import com.litesoftwares.coingecko.CoinGeckoApiClient;
import com.litesoftwares.coingecko.domain.Coins.CoinMarkets;
import com.litesoftwares.coingecko.domain.Ping;
import com.litesoftwares.coingecko.impl.CoinGeckoApiClientImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CoinGeckoApiClientWrapper {

   private CoinGeckoApiClient coinGeckoApiClient;
   private Ping ping;

    public boolean isAvailable() {
        coinGeckoApiClient = new CoinGeckoApiClientImpl();
        Ping ping = coinGeckoApiClient.ping();
        coinGeckoApiClient.shutdown();
        return ping.getGeckoSays().equals("(V3) To the Moon!");
    }

    public List<CoinMarkets> getCoinMarkets(String currency, int countPerPage ,int pageNumber) {
        return coinGeckoApiClient
                .getCoinMarkets(currency, "", "", countPerPage, pageNumber, false, "");
    }
    public void start() {
        coinGeckoApiClient  = new CoinGeckoApiClientImpl();
    }
    public void shootDawn() {
        if (Objects.nonNull(coinGeckoApiClient)) {
            coinGeckoApiClient.shutdown();
        }
    }
}
