-- changeset :01_create_table_coins_usd
-- Name: coins_usd; Type: TABLE; schema: public

CREATE TABLE IF NOT EXISTS public.coins_usd
(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    coin_rank BIGINT NOT NULL,
    coin_id VARCHAR(50) NOT NULL,
    coin_name VARCHAR(50) NOT NULL,
    coin_symbol VARCHAR(25) NOT NULL,
    current_price DECIMAL NOT NULL,
    Ath_price DECIMAL NOT NULL,
    date_of_Ath_price VARCHAR(255) NOT NULL,
    percentage_change_from_ath DECIMAL NOT NULL,
    max_price_24h DECIMAL NOT NULL,
    minimum_price_24h DECIMAL NOT NULL,
    changing_price_24h DECIMAL NOT NULL,
    changing_price_in_percentage24h DECIMAL NOT NULL,
    coin_image VARCHAR(512) NOT NULL,
    coin_url VARCHAR(512) NOT NULL
    );