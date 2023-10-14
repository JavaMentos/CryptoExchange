package ru.home.crypto.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static ru.home.crypto.controller.constant.Endpoints.COIN_FULL_PATH;

@RestController
@RequestMapping(COIN_FULL_PATH)
@RequiredArgsConstructor
public class CoinController {


}
