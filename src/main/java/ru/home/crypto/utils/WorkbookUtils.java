package ru.home.crypto.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Утилитарный класс для работы с workbook
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WorkbookUtils {
    /**
     * Формат файла - расширение Excel
     */
    public static final String FORMAT_FILE = ".xlsx";
    /**
     * Наименование листа
     */
    public static final String SHEET_NAME = "Coins";
    /**
     * Наименование столбца, содержащего идентификаторы монеты
     */
    public static final String COIN_ID = "ID";
    /**
     * Наименование столбца, содержащего названия монеты
     */
    public static final String COIN_NAME = "Имя";
    /**
     * Наименование столбца, содержащего рейтинг монеты
     */
    public static final String COIN_RATING = "Рейтинг";
    /**
     * Наименование столбца, содержащего тикеры (уникальное короткое наименование) монеты
     */
    public static final String COIN_TICKER = "Тикер";
    /**
     * Наименование столбца, содержащего стоимость монеты
     */
    public static final String COIN_AMOUNT = "Цена USDT";
}
