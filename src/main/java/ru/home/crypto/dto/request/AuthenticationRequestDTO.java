package ru.home.crypto.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record AuthenticationRequestDTO(
        @NotEmpty
        @NotNull
        String username,
        @NotEmpty
        @NotNull
        String password
) {
}