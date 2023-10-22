package ru.home.crypto.mapper;

import com.litesoftwares.coingecko.domain.Coins.CoinMarkets;
import org.mapstruct.Mapper;
import java.time.LocalDateTime;
import org.mapstruct.Mapping;
import ru.home.crypto.dto.CoinExportDTO;
import ru.home.crypto.entity.Coin;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class})
public interface CoinMapper {
    @Mapping(source = "marketCapRank", target = "coinRank")
    @Mapping(source = "id", target = "coinId")
    @Mapping(source = "name", target = "coinName")
    @Mapping(source = "symbol", target = "coinSymbol")
    @Mapping(target = "lastUpdatedAt", expression = "java(LocalDateTime.now())")
    @Mapping(source = "currentPrice", target = "currentPrice")
    @Mapping(source = "ath", target = "athPrice")
    @Mapping(source = "athDate", dateFormat = "dd.MM.yyyy", target = "dateOfAthPrice")
    @Mapping(source = "athChangePercentage", target = "percentageChangeFromAth")
    @Mapping(source = "high24h", target = "maxPrice24h")
    @Mapping(source = "low24h", target = "minimumPrice24h")
    @Mapping(source = "priceChange24h", target = "changingPrice24h")
    @Mapping(source = "priceChangePercentage24h", target = "changingPriceInPercentage24h")
    @Mapping(source = "image", target = "coinImage")
    @Mapping(target = "coinUrl", expression = "java(\"https://www.coingecko.com/en/coins/\" + coinMarkets.getId())")
    Coin coinMarketsToCoin(CoinMarkets coinMarkets);

    CoinExportDTO coinToCoinExportDTO(Coin coin);
}
