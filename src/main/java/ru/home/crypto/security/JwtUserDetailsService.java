package ru.home.crypto.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.home.crypto.entity.security.User;
import ru.home.crypto.security.jwt.JwtUser;
import ru.home.crypto.security.jwt.JwtUserFactory;
import ru.home.crypto.service.UserService;

/**
 * Класс JwtUserDetailsService предоставляет средства для поиска пользователей в системе.
 * Реализует стандартный интерфейс Spring Security UserDetailsService.
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    /**
     * Ищет пользователя по его имени. Если пользователь не найден, выбрасывает исключение.
     *
     * @param username Имя пользователя, которого нужно найти.
     * @return Детали пользователя (объект UserDetails), если он был найден.
     * @throws UsernameNotFoundException Если пользователь с указанным именем не найден.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("IN loadUserByUsername - user with username: {} successfully loaded", username);
        return jwtUser;
    }
}