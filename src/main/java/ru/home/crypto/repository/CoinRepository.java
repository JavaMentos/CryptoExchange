package ru.home.crypto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.home.crypto.entity.Coin;

@Repository
public interface CoinRepository extends JpaRepository<Coin, Long> {

}