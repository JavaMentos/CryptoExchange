import com.litesoftwares.coingecko.CoinGeckoApiClient;
import com.litesoftwares.coingecko.impl.CoinGeckoApiClientImpl;

//package com.cryptoexchange;
//
//import com.cryptoexchange.entity.Coin;
//import com.litesoftwares.coingecko.CoinGeckoApiClient;
//import com.litesoftwares.coingecko.constant.Currency;
//import com.litesoftwares.coingecko.domain.Coins.*;
//import com.litesoftwares.coingecko.domain.Coins.CoinData.SparklineIn7d;
//import com.litesoftwares.coingecko.domain.Exchanges.ExchangeById;
//import com.litesoftwares.coingecko.impl.CoinGeckoApiClientImpl;
//
//import java.text.SimpleDateFormat;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.*;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
public class test {
//
////    static {
////        new Coin("nexo", "NEXO", "USD", "nexo");
////        new Coin("curve-dao-token", "Curve DAO Token", "USD", "crv");
////        new Coin("dash", "Dash", "USD", "dash");
////        new Coin("adamant", "Adamant", "USD", "addy");
////        new Coin("aeon", "Aeon", "USD", "aeon");
////
////    }
//
    public static void main(String[] args) {

        CoinGeckoApiClient client = new CoinGeckoApiClientImpl();
        while(true) {
            try {
                System.out.println(client.ping());
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
//        CoinGeckoApiClient client = new CoinGeckoApiClientImpl();
//
//        List list = new ArrayList(List.of("123","asd","321"));
//        System.out.println(list.indexOf("ASD"));
//
//
//
//
//        //TODO реализация команды SHOW COIN INFO, нужно показать максимальную историю по монете, за доступные периоды
//        //TODO Переписать метод CoinGecko, там идет обращение к БД по каждой монете, нужно брать листом и раскладывать по полям в классе
//
//        System.out.println(client.getCoinById("storj"));
////        for (int i = 0; i < 10; i++) {
////
////
////            List<CoinMarkets> coinMarkets = client.getCoinMarkets(Currency.USD, "", "", 250, i, true, "");
////            for (CoinMarkets coinMarket : coinMarkets) {
////
////
////                System.out.println("Market cap rank - " + coinMarket.getMarketCapRank());
////                System.out.println("Name coin - " + coinMarket.getName());
////                System.out.println("Id coin - " + coinMarket.getId());
////                System.out.println("Symbol coin - " + coinMarket.getSymbol());
////                System.out.println("Date ATH - " + formattedTime(coinMarket.getAthDate()));
////                System.out.println("Max price - " + coinMarket.getAth());
////                System.out.println("Current price - " + coinMarket.getCurrentPrice());
////                System.out.println("Price change 24h - " + coinMarket.getPriceChange24h());
////                System.out.println("Price change in percentage 24h - " + coinMarket.getPriceChangePercentage24h());
////                System.out.println("Max price 24h - " + coinMarket.getHigh24h());
////                System.out.println("Minimum price 24ч - " + coinMarket.getLow24h());
////                System.out.println("Get image coin - " + coinMarket.getImage());
////                System.out.println("Get url - https://www.coingecko.com/en/coins/"+coinMarket.getId());
////                System.out.println("Get spark line 7d - " + coinMarket.getSparklineIn7d());
////
////            }
////            return;
////        }
////        client.shutdown();
////
////        System.out.println("ВСЕ");
////
////
////        for (CoinMarkets markets : coinsMarket) {
////            for (Coin coin : Coin.coins) {
////                if(markets.getId().equals(coin.getCoinId())) System.out.println("Совпало " + markets.getName() +" - "+ coin.getCoinName());;
////
////            }
////        }
////
////        System.out.println("ВСЕ - 2");
//
//
//
//    }
//        public static String formattedTime (String date){
////        String date = "2011-08-11T01:23:45.678Z";
//
//            DateTimeFormatter dft2 = DateTimeFormatter
//                    .ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
//            LocalDateTime localDateTime = LocalDateTime.parse(date, dft2);
//
//            return DateTimeFormatter.ofPattern("yyyy/MM/dd - HH:mm:ss").format(localDateTime);
//
//    }
}