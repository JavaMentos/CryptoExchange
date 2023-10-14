package ru.home.crypto.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * DTO запрос для создания JWT токена.
 * @param secretPhrase фраза для создания токена
 */
public record TokenRequestDTO(
        @NotEmpty
        @NotNull
        String secretPhrase
) {
}
