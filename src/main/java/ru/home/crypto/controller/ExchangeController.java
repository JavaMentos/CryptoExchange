package ru.home.crypto.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static ru.home.crypto.controller.constant.Endpoints.*;

@RestController
@RequestMapping(EXCHANGE_PATH)
@RequiredArgsConstructor
public class ExchangeController {

    @GetMapping(COIN_FULL_PATH)
    public String getCoin() {

        return "Okey";
    }
}
