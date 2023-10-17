package ru.home.crypto.security.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Класс JwtConfigurer отвечает за настройку фильтра JWT в цепочке фильтров безопасности.
 */
@RequiredArgsConstructor
public class JwtConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * Настраивает объект HttpSecurity, добавляя в его цепочку фильтров JwtTokenFilter.
     * Фильтр JwtTokenFilter будет запущен перед фильтром UsernamePasswordAuthenticationFilter.
     *
     * @param httpSecurity Объект HttpSecurity для настройки безопасности.
     */
    @Override
    public void configure(HttpSecurity httpSecurity) {
        JwtTokenFilter jwtTokenFilter = new JwtTokenFilter(jwtTokenProvider);
        httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}