package ru.home.crypto.security.jwt;

import org.springframework.security.core.AuthenticationException;

/**
 * Класс JwtAuthenticationException представляет собой исключение, связанное с аутентификацией JWT.
 * Этот класс используется для обозначения проблем, возникающих при аутентификации с использованием JWT.
 */
public class JwtAuthenticationException extends AuthenticationException {

    /**
     * Создаёт новое исключение JwtAuthenticationException.
     *
     * @param msg Сообщение об ошибке, описывающее причину исключения.
     */
    public JwtAuthenticationException(String msg) {
        super(msg);
    }
}