package ru.home.crypto.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * Класс конфигурации для свойств JWT (Json Web Token).
*/
@Configuration
@ConfigurationProperties(prefix = "jwt")
@Setter
@Getter
public class JwtConfiguration {
    private String secret;
    private Duration lifeTime;
}
