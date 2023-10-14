package ru.home.crypto.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.home.crypto.dto.request.TokenRequestDTO;
import ru.home.crypto.dto.response.JwtResponseDTO;
import ru.home.crypto.utils.CommonChecks;
import ru.home.crypto.utils.JwtTokenUtils;

import static ru.home.crypto.controller.constant.Endpoints.CREATE_TOKEN_FULL_PATH;
import static ru.home.crypto.controller.constant.Endpoints.TOKEN_FULL_PATH;

/**
 * Controller для создания токена
 */
@RestController
@RequestMapping(TOKEN_FULL_PATH)
@RequiredArgsConstructor
public class TokenController {

    private final JwtTokenUtils jwtTokenUtils;
    private final CommonChecks commonChecks;

    /**
     * Создает новый токен для доступа к CoinController.
     *
     * @return String Созданный токен.
     */
    @PostMapping(CREATE_TOKEN_FULL_PATH)
    public JwtResponseDTO createToken(@Valid @RequestBody TokenRequestDTO request) {
        commonChecks.checkSecretPhrase(request.secretPhrase());
        String token = jwtTokenUtils.generateToken();

        return new JwtResponseDTO(token);
    }
}
