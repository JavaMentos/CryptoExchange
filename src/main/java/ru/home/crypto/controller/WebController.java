package ru.home.crypto.controller;

import ru.home.crypto.entity.CheckCoinId;
import ru.home.crypto.entity.Coin;
import ru.home.crypto.repository.CoinRepository;
import ru.home.crypto.service.impl.CoinUpdateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class WebController {

    private Coin coin;
    private CoinRepository coinRepository;

    @Autowired
    public WebController(CoinRepository coinRepository, CoinUpdateServiceImpl coinGeckoServiceImple){
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

//    public Coin parseCoin(String line){
//        String[] strings = line.split(",");
//
//        return new Coin(
//                strings[0],
//                strings[2],
//                "USD",
//                strings[1]
//        );
//    }


}
