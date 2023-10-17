package ru.home.crypto.security.jwt;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.home.crypto.entity.security.Role;
import ru.home.crypto.entity.security.Status;
import ru.home.crypto.entity.security.User;

import java.util.Collection;

/**
 * Класс JwtUserFactory предоставляет средства для создания объектов JwtUser.
 * Этот класс не может быть инстанциирован, все его методы статические.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JwtUserFactory {

    /**
     * Создаёт объект JwtUser на основе предоставленного пользователя.
     *
     * @param user Пользователь, на основе которого будет создан объект JwtUser.
     * @return объект JwtUser, который содержит информацию о пользователе и его ролях.
     */
    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getRoles()),
                user.getStatus().equals(Status.ACTIVE)
        );
    }

    /**
     * Преобразует коллекцию ролей в коллекцию GrantedAuthority.
     *
     * @param userRoles Коллекция ролей, которые нужно преобразовать.
     * @return Коллекция объектов типа GrantedAuthority, соответствующая предоставленным ролям пользователя.
     */
    private static  Collection<? extends GrantedAuthority> mapToGrantedAuthorities(Collection<Role> userRoles) {
        return userRoles.stream()
                .map(role ->
                        new SimpleGrantedAuthority(role.getName())).toList();
    }
}