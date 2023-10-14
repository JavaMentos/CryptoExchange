package ru.home.crypto.controller.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Endpoints имена
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Endpoints {
    public static final String PATH_DIVIDER = "/";
    public static final String CREATE_TOKEN = "create_token";
    public static final String TOKEN = "token";
    public static final String COINS = "coins";
    public static final String CREATE_TOKEN_FULL_PATH = PATH_DIVIDER + CREATE_TOKEN;
    public static final String TOKEN_FULL_PATH = PATH_DIVIDER + TOKEN;
    public static final String COIN_FULL_PATH = PATH_DIVIDER + COINS;
}
