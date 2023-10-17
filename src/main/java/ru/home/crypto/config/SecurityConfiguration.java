package ru.home.crypto.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import ru.home.crypto.security.jwt.JwtConfigurer;
import ru.home.crypto.security.jwt.JwtTokenProvider;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;
import static ru.home.crypto.config.constant.RequestPath.*;


/**
 * Настройка безопасности.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final String ROLE = "ADMIN";

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    private final JwtTokenProvider jwtTokenProvider;

    /**
     * Доступ API без аутентификации.
     */
    @Bean
    @Order(1)
    public SecurityFilterChain publicSecurity(HttpSecurity http) throws Exception {
        http.securityMatchers(matchers ->
                        matchers.requestMatchers(
                                ACTUATOR + ALL,
                                AUTH + ALL))
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .csrf(csrf -> csrf.requireCsrfProtectionMatcher(request -> false))
                .cors(withDefaults())
                .apply(new JwtConfigurer(jwtTokenProvider));
        return http.build();
    }

    /**
     * Доступ к API, требующем аутентификации.
     */
    @Bean
    @Order(2)
    public SecurityFilterChain authenticatedSecurity(HttpSecurity http) throws Exception {
        http.securityMatcher(ALL)
                .authorizeHttpRequests(auth -> auth.anyRequest().hasAnyRole(ROLE))
                .csrf(csrf -> csrf.requireCsrfProtectionMatcher(request -> false))
                .cors(withDefaults())
                .apply(new JwtConfigurer(jwtTokenProvider));
        return http.build();
    }

    /**
     * Конфигурация CORS (Cross-Origin Resource Sharing) позволяет контролировать доступ к ресурсам
     * на сервере из разных источников (доменов).
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}