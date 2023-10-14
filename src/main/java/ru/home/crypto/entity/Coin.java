package ru.home.crypto.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "coins_usd")
public class Coin {
    @Id
    @Column(name = "coin_id", length = 50)
    private String coinId;
    @Column(name = "coin_rank")
    private long coinRank;
    @Column(name = "coin_name", length = 50)
    private String coinName;
    @Column(name = "coin_symbol", length = 25)
    private String coinSymbol;
    @Column(name = "current_price")
    private BigDecimal currentPrice;
    @Column(name = "Ath_price")
    private BigDecimal AthPrice;
    @Column(name = "date_of_Ath_price")
    private String dateOfAthPrice;
    @Column(name = "percentage_change_from_ath")
    private BigDecimal percentageChangeFromAth;
    @Column(name = "max_price_24h")
    private BigDecimal maxPrice24h;
    @Column(name = "minimum_price_24h")
    private BigDecimal minimumPrice24h;
    @Column(name = "changing_price_24h")
    private BigDecimal changingPrice24h;
    @Column(name = "changing_price_in_percentage24h")
    private BigDecimal changingPriceInPercentage24h;
    @Column(name = "coin_image", length = 512)
    private String coinImage;
    @Column(name = "coin_url", length = 512)
    private String coinUrl;
}
