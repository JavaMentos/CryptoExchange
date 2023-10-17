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
    public static final String USERS = "users";
    public static final String ALL_COINS = "all_coins";
    public static final String LOGIN_FULL_PATH = PATH_DIVIDER + LOGIN;
    public static final String AUTH_FULL_PATH = PATH_DIVIDER + AUTH;
    public static final String EXCHANGE_FULL_PATH = PATH_DIVIDER + EXCHANGE;
    public static final String USERS_FULL_PATH = PATH_DIVIDER + USERS;
    public static final String ALL_COINS_FULL_PATH = PATH_DIVIDER + ALL_COINS;
}
