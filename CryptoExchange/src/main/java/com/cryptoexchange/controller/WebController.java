package com.cryptoexchange.controller;

import com.cryptoexchange.entity.CheckCoinId;
import com.cryptoexchange.entity.Coin;
import com.cryptoexchange.repository.CoinRepository;
import com.cryptoexchange.serviceImpl.CoinGeckoServiceImple;
import com.litesoftwares.coingecko.CoinGeckoApiClient;
import com.litesoftwares.coingecko.constant.Currency;
import com.litesoftwares.coingecko.domain.Coins.CoinMarkets;
import com.litesoftwares.coingecko.impl.CoinGeckoApiClientImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Controller
//@RestController
public class WebController {

    private Coin coin;
    private File file;
    private CoinRepository coinRepository;

    @Autowired
    public WebController(CoinRepository coinRepository, CoinGeckoServiceImple coinGeckoServiceImple){
        this.coinRepository = coinRepository;

//        Thread coingecko = new Thread(coinGeckoServiceImple);
//        coingecko.start();

    }

    @GetMapping("/")
    public String mainPage(Model model) {
        coin = new Coin();

        model.addAttribute("createCoin",coin);

        model.addAttribute("getCoin", new CheckCoinId());

        return "main";
//        return "tempMain";
    }

    @GetMapping("/getAllCoins")
    public String result(Model model) {
        model.addAttribute("allCoins",coinRepository.getAllCoins());
//        System.out.println(coinRepository.getAllCoins());


//        CoinGeckoApiClient client = new CoinGeckoApiClientImpl();
//        for (int i = 0; i < 10; i++) {
//
//
//            List<CoinMarkets> coinMarkets = client.getCoinMarkets(Currency.USD, "", "", 250, i, true, "");
//            for (CoinMarkets coinMarket : coinMarkets) {
//
//                coinRepository.save(new Coin( coinMarket.getMarketCapRank()
//                        ,coinMarket.getId()
//                        ,coinMarket.getName()
//                        ,coinMarket.getSymbol()
//                        ,Currency.USD
//                        ,formattedTime(coinMarket.getAthDate())
//                        ,coinMarket.getCurrentPrice()
//                        ,coinMarket.getAth()
//                        ,coinMarket.getHigh24h()
//                        ,coinMarket.getLow24h()
//                        ,coinMarket.getPriceChange24h()
//                        ,coinMarket.getPriceChangePercentage24h()
//                        ,coinMarket.getImage()
//                        ,"https://www.coingecko.com/en/coins/"+coinMarket.getId()
//                ));
//
//
//            }
//        }
//        client.shutdown();
//
//        System.out.println("ВСЕ");




        return "getAllCoins";
    }

    public String formattedTime (String date){
//        String date = "2011-08-11T01:23:45.678Z";

        DateTimeFormatter dft2 = DateTimeFormatter
                .ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        LocalDateTime localDateTime = LocalDateTime.parse(date, dft2);

        return DateTimeFormatter.ofPattern("yyyy/MM/dd - HH:mm:ss").format(localDateTime);

    }

    //TODO Переделать метод
    @GetMapping("/writeFile")
    public String writeFile(Model model) {

        file = new File("src/main/resources/coins.csv");

//        try {
//            List<Coin> coinList  = new BufferedReader(new FileReader(file)).lines()
//                    .skip(1)
//                    .map(c -> parseCoin(c))
//                    .collect(Collectors.toList());

//            List<Coin> coinList  = new BufferedReader(new FileReader(file)).lines()
//                    .skip(1)
//                    .map(this::parseCoin)
//                    .collect(Collectors.toList());
//
//            coinRepository.saveAll(coinList);

            model.addAttribute("file",coinRepository.getAllCoins());

//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

        return "writeFile";
    }

    @GetMapping("/deleteAllCoins")
    public String deleteAll(Model model) {

        coinRepository.deleteTableCoins();
        coinRepository.updateGeneratedValue();
        model.addAttribute("allCoins",coinRepository.getAllCoins());
//        Coin.coins.clear();
        System.out.println(coinRepository.getAllCoins());

        return "deleteAllCoins";
    }

    @GetMapping("/create")
    public String getNewCoin(){

        return "create";
    }

    @PostMapping("/create")
    public String acceptData(@ModelAttribute("createCoin") Coin coin, Model model){
        System.out.println(coin.getCoinId());
        model.addAttribute("newCreate", coin);
//        Coin.coins.add(coin);
        coinRepository.save(coin);

        return "create";
    }

    @GetMapping("/resultCoinById")
    public String getCoin(){
        return "resultCoinById";
    }

    @PostMapping("/resultCoinById")
    public String acceptCoin(@ModelAttribute("getCoin") CheckCoinId coinId, Model model){
        System.out.println("Вывод acceptCoin: "+coinId.getCoinId());

        model.addAttribute("coinById", coinRepository.getCoin(coinId.getCoinId()));

        return "resultCoinById";
    }

    public Coin parseCoin(String line){
        String[] strings = line.split(",");

        return new Coin(
                strings[0],
                strings[2],
                "USD",
                strings[1]
        );
    }


}
