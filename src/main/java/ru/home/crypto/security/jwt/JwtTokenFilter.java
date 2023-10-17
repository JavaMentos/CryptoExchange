package ru.home.crypto.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

/**
 * Фильтр JwtTokenFilter отвечает за обработку и валидацию JWT-токенов.
 */
@RequiredArgsConstructor
public class JwtTokenFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    /**
     * Выполняет фильтрацию запросов, извлекая и проверяя JWT-токен.
     * Если токен валидный, он устанавливает аутентификацию в контексте безопасности.
     *
     * @param req Исходный запрос.
     * @param res Ответ на запрос.
     * @param filterChain Цепочка фильтров, которые следует выполнить после текущего фильтра.
     * @throws IOException В случае ошибки ввода/вывода.
     * @throws ServletException В случае ошибки при работе сервлета.
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {

        String token = jwtTokenProvider.resolveToken((HttpServletRequest) req);
        if (token != null && jwtTokenProvider.validateToken(token)) {
            Authentication auth = jwtTokenProvider.getAuthentication(token);

            if (auth != null) {
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(req, res);
    }
}