package ru.home.crypto.security.jwt;


import io.jsonwebtoken.*;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.home.crypto.config.JwtConfiguration;
import ru.home.crypto.entity.security.Role;

import java.util.*;

/**
 * Класс JwtTokenProvider отвечает за генерацию, валидацию JWT токенов
 * и их использование для аутентификации пользователей.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final JwtConfiguration jwtConfiguration;
    private final UserDetailsService userDetailsService;
    private String secret;

    /**
     * Создаёт новый объект BCryptPasswordEncoder.
     *
     * @return Объект BCryptPasswordEncoder.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Инициализация секретного ключа после создания бина.
     */
    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder()
                .encodeToString(jwtConfiguration.getSecret().getBytes());
    }


    /**
     * Создаёт JWT токен на основе имени пользователя и набора его ролей.
     *
     * @param username Имя пользователя.
     * @param roles    Список ролей пользователя.
     * @return Сгенерированный JWT токен.
     */
    public String createToken(String username, List<Role> roles) {
        Date issuedDate = new Date();
        Date expiredDate = new Date(issuedDate.getTime() +
                jwtConfiguration.getLifeTime().toMillis());

        return Jwts.builder()
                .setSubject(username)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(issuedDate)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    /**
     * Возвращает объект Authentication на основе переданного токена.
     *
     * @param token JWT токен.
     * @return Объект Authentication.
     */
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    /**
     * Извлекает имя пользователя из JWT токена.
     *
     * @param token JWT токен.
     * @return Имя пользователя.
     */
    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * Извлекает JWT токен из запроса.
     *
     * @param req HttpServletRequest запрос.
     * @return JWT токен или null, если токен не найден.
     */
    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    /**
     * Валидирует JWT токен.
     * Если токен истек или недействителен, выбрасывается исключение JwtAuthenticationException.
     *
     * @param token JWT токен.
     * @return true, если токен действителен и не истёк, иначе - false.
     * @throws JwtAuthenticationException при проблемах с валидацией токена.
     */
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);

            if (claims.getBody().getExpiration().before(new Date())) return false;

            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtAuthenticationException("JWT token is expired or invalid");
        }
    }

    /**
     * Извлекает роли из JWT токена.
     *
     * @param userRoles список ролей.
     * @return коллекция ролей.
     */
    private List<String> getRoleNames(List<Role> userRoles) {
        List<String> result = new ArrayList<>();

        userRoles.forEach(role -> {
            result.add(role.getName());
        });

        return result;
    }
}