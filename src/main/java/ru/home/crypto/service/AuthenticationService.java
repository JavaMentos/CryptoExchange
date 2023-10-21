package ru.home.crypto.service;

import ru.home.crypto.dto.request.AuthenticationRequestDTO;
import ru.home.crypto.dto.response.AuthenticationResponseDTO;
import ru.home.crypto.entity.security.User;

/**
 * Сервис аутентификации, предоставляющий методы для поиска пользователя по имени
 * и для процесса аутентификации пользователя с последующей генерацией JWT токена.
 */
public interface AuthenticationService {

    /**
     * Ищет пользователя по его имени.
     *
     * @param username имя пользователя, которое нужно найти.
     * @return найденный пользователь, если он существует, иначе null.
     */
    User findByUsername(String username);

    /**
     * Аутентифицирует пользователя и генерирует JWT токен.
     *
     * @param requestDTO объект запроса на аутентификацию, содержащий учетные данные пользователя.
     * @return объект ответа аутентификации, содержащий имя пользователя и сгенерированный JWT токен.
     */
    AuthenticationResponseDTO authenticateUserAndGenerateToken(AuthenticationRequestDTO requestDTO);
}
