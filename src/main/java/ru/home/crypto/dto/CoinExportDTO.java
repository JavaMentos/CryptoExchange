package ru.home.crypto.dto;

import java.math.BigDecimal;


public record CoinExportDTO(
        String coinId,
        long coinRank,
        String coinName,
        String coinSymbol,
        BigDecimal currentPrice
) {
}