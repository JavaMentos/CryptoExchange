package ru.home.crypto.dto.response;

public record AuthenticationResponseDTO(
        String username,
        String token
) {
}