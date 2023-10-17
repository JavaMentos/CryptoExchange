package ru.home.crypto.service;


import ru.home.crypto.entity.security.User;

public interface UserService {
    User findByUsername(String username);
}