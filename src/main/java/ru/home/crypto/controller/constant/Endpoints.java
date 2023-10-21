package ru.home.crypto.controller.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Endpoints имена
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Endpoints {
    public static final String PATH_DIVIDER = "/";
    public static final String AUTH = "auth";
    public static final String LOGIN = "login";
    public static final String EXCHANGE = "exchange";
    public static final String COIN = "coin";
    public static final String LOGIN_FULL_PATH = PATH_DIVIDER + LOGIN;
    public static final String AUTH_PATH = PATH_DIVIDER + AUTH;
    public static final String EXCHANGE_PATH = PATH_DIVIDER + EXCHANGE;
    public static final String COIN_FULL_PATH = PATH_DIVIDER + COIN;
}
