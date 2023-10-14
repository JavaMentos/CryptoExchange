package ru.home.crypto.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.home.crypto.service.CoinUpdateService;

@Service
@RequiredArgsConstructor
public class UpdateCoinsScheduler {

    private final CoinUpdateService coinUpdateService;

    @Scheduled(initialDelayString = ("${scheduling.initialDelay}"), fixedDelayString = "${scheduling.fixedDelay}")
    void updateCoins() {

//        coinUpdateService.updateCoins();
    }
}
