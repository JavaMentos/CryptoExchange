package ru.home.crypto.utils;

import jakarta.ws.rs.BadRequestException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.home.crypto.config.SecretPhraseConfiguration;

/**
 * Класс проверяет входящие параметры у дтошек
 */
@AllArgsConstructor
@Component
public class CommonChecks {

    private final SecretPhraseConfiguration secretPhraseConfiguration;

    public void checkSecretPhrase(String secret) {
        if(!secretPhraseConfiguration.getPhrase().equals(secret))
            throw new BadRequestException("Не корректная фраза");
    }
}
