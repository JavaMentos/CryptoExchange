package ru.home.crypto.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CoinUtils {
    private static final String url = "https://www.coingecko.com/en/coins/";
    public static String createUrlForCoin(String coinId) {
        return url + coinId;
    }
}
