package ru.home.crypto.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.home.crypto.config.JwtConfiguration;
import ru.home.crypto.dto.request.TokenRequestDTO;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtTokenUtils {

    private final JwtConfiguration jwtConfiguration;

    /**
     * Генерирует JWT токен.
     *
     * @return Сгенерированный JWT токен.
     */
    public String generateToken() {
        Date issuedDate = new Date();
        Date expiredDate = new Date(issuedDate.getTime() +
                jwtConfiguration.getLifeTime().toMillis());
        String resourceKey = "resource";
        String paramsKey = "params";

        return Jwts.builder()
                .setIssuedAt(issuedDate)
                .setExpiration(expiredDate)
                .claim(resourceKey, new HashMap<>())
                .claim(paramsKey, new HashMap<>())
                .signWith(Keys.hmacShaKeyFor(jwtConfiguration.getSecret().getBytes()),
                        SignatureAlgorithm.HS256)
                .compact();
    }
}