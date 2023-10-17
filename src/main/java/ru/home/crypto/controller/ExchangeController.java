package ru.home.crypto.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static ru.home.crypto.controller.constant.Endpoints.ALL_COINS_FULL_PATH;
import static ru.home.crypto.controller.constant.Endpoints.EXCHANGE_FULL_PATH;

@RestController
@RequestMapping(EXCHANGE_FULL_PATH)
@RequiredArgsConstructor
public class ExchangeController {

    @GetMapping(ALL_COINS_FULL_PATH)
    public String getAllCoins() {

        return "Okey";
    }
}
