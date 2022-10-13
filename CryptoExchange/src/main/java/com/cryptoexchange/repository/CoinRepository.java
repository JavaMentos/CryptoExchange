package com.cryptoexchange.repository;

import com.cryptoexchange.entity.Coin;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public interface CoinRepository extends CrudRepository<Coin, Long> {

    //Получить и вывести все монеты
    @Query(value = "select * from coin", nativeQuery = true)
    Iterable<Coin> getAllCoins();

    //Получить лист коинов из БД
    @Query(value = "select * from coin", nativeQuery = true)
    List<Coin> getAllCoinList();

    //TODO разобраться с методом, скорее всего нужно удалить
    //получить list<String> монет с ценой
    @Query(value = "select coin_id, current_course from coin", nativeQuery = true)
    List<String> getListCoinIdCurrentPrice();

    //получить монету и вывести монету по coin_id(уникальное имя)
    @Query(value = "select * from coin where coin_Id = :getCoin", nativeQuery = true)
    Iterable<Coin> getCoin(@Param("getCoin") String coin_id);

    //Получить количество монет в БД
    @Query(value = "select count(*) from coin", nativeQuery = true)
    int getCount();

    //Удаляем таблицу Coin, пересоздается при запуске проекта
    @Modifying
    @Query(value = "DELETE from coin", nativeQuery = true)
    void deleteTableCoins();

    //Обновляем данные в БД, по параметрам
    @Modifying
    @Query(value = "update coin set coin_rank = :coinRank, current_price = :currentPrice, date_ath = :dateAth, max_price = :maxPrice, max_price24h = :maxPrice24h, minimum_price24h = :minimumPrice24h, changing_price24h = :changingPrice24h, changing_price_in_percentage24h = :changingPriceInPercentage24h where coin_id = :coinId", nativeQuery = true)
    void updateFieldsWithPrice(@Param("coinId") String coin_id
            , @Param("coinRank") long coin_rank
            , @Param("currentPrice") double current_price
            , @Param("dateAth") String date_ath
            , @Param("maxPrice") double max_price
            , @Param("maxPrice24h") double max_price24h
            , @Param("minimumPrice24h") double minimum_price24h
            , @Param("changingPrice24h") double changing_price24h
            , @Param("changingPriceInPercentage24h") double changing_price_in_percentage24h);


    //Сбрасывает скивенс на 1
    @Modifying
    @Query(value = "update hibernate_sequence set next_val = 1", nativeQuery = true)
    void updateGeneratedValue();

}