package ru.home.crypto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableScheduling
@EnableWebSecurity
public class CryptoExchangeApplication {
    public static void main(String[] args) {
        SpringApplication.run(CryptoExchangeApplication.class, args);
    }
}

//TODO Настроить глобальный обработчик эксепшинов Advice
//TODO Начать документировать методы и класс
//TODO Начать прикручивать кафку