//package com.cryptoexchange.controller;
//
//import com.cryptoexchange.entity.Coin;
//import com.cryptoexchange.serviceImpl.CoinGeckoServiceImple;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import com.cryptoexchange.repository.CoinRepository;
//
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@RestController
//public class JsonController {
//
//    private CoinRepository coinRepository;
//
//    @Autowired
//    public JsonController(CoinRepository coinRepository){
//        this.coinRepository = coinRepository;
//    }
//
//    @GetMapping("/getMapCoinIdCurrentCourse")
//    public ResponseEntity<Map<String,String>> getMapCoinIdCurrentCourse(){
////        toMap(dto::getId,dto::getPrice);
//        List<String> str = coinRepository.getListCoinIdCurrentPrice();
////        System.out.println(str.toString());
////        Map<String, String> map =
////                str.stream().collect(Collectors.toMap(String::getKey, String::getValue));
////        System.out.println(map.toString());
////        ResponseEntity.ok(hashMap);
//        Map<String, String> map = str.stream().collect(Collectors.toMap(e -> ((String) e), e -> (String) e));
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            System.out.println(" Ключи " + entry.getKey());
//            System.out.println(" Значение " + entry.getValue());
//        }
//
//        return null;
//    }
//
//    @GetMapping("/getListCoins")
//    public ResponseEntity<List<Coin>> getListCoins(){
//
//        return new ResponseEntity<>(coinRepository.getAllCoinList(), HttpStatus.OK);
//    }
//
//    @GetMapping("/return-something2")
//    public ResponseEntity<List<Coin>> returnSmth(){
//        List<Coin> coin = coinRepository.getAllCoinList();
//        System.out.println(coin.toString());
//
//
//        return new ResponseEntity<>(coin, HttpStatus.OK);
//    }
//}
