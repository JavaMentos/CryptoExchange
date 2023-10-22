package ru.home.crypto.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.home.crypto.service.ExchangeService;

import static ru.home.crypto.controller.constant.Endpoints.*;

@RestController
@RequestMapping(EXCHANGE_PATH)
@RequiredArgsConstructor
public class ExchangeController {
    private final ExchangeService exchangeService;

    @GetMapping(COIN_FULL_PATH)
    public String getCoin() {

        return "Okey";
    }

    @GetMapping(ALL_COINS_FULL_PATH)
    public ResponseEntity<Resource> getAllCoins() {

        byte[] exportedZip  = exchangeService.exportCoinsToZippedXlsx();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", "coins_data.zip");
        headers.setContentLength(exportedZip.length);

        ByteArrayResource resource = new ByteArrayResource(exportedZip);

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }
}
