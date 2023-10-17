package ru.home.crypto.config.constant;

/**
 * Константы пути для secutiry.
 */
public final class RequestPath {
    public static final String DELIMITER = "/";
    public static final String ACTUATOR = DELIMITER + "actuator";
    public static final String AUTH = DELIMITER + "auth";
    public static final String ANY = "**";
    public static final String ALL = DELIMITER + ANY;
}