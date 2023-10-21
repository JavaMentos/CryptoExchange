package ru.home.crypto.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.home.crypto.dto.request.AuthenticationRequestDTO;
import ru.home.crypto.dto.response.AuthenticationResponseDTO;
import ru.home.crypto.service.AuthenticationService;

import static ru.home.crypto.controller.constant.Endpoints.AUTH_PATH;
import static ru.home.crypto.controller.constant.Endpoints.LOGIN_FULL_PATH;

/**
 * REST controller for authentication requests (login, logout, register, etc.)
 *
 * @author Eugene Suleimanov
 * @version 1.0
 */

@RestController
@RequiredArgsConstructor
@RequestMapping(value = AUTH_PATH)
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping(LOGIN_FULL_PATH)
    public AuthenticationResponseDTO login(@Valid @RequestBody AuthenticationRequestDTO requestDto) {
        return authenticationService.authenticateUserAndGenerateToken(requestDto);
    }
}