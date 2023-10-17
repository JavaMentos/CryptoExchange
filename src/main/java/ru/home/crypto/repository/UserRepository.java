package ru.home.crypto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.home.crypto.entity.security.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String name);
}