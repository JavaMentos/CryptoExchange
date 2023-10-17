package ru.home.crypto.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.home.crypto.dto.request.AuthenticationRequestDTO;
import ru.home.crypto.dto.response.AuthenticationResponseDTO;
import ru.home.crypto.entity.security.User;
import ru.home.crypto.security.jwt.JwtTokenProvider;
import ru.home.crypto.service.UserService;

import static ru.home.crypto.controller.constant.Endpoints.AUTH_FULL_PATH;
import static ru.home.crypto.controller.constant.Endpoints.LOGIN_FULL_PATH;

/**
 * REST controller for authentication requests (login, logout, register, etc.)
 *
 * @author Eugene Suleimanov
 * @version 1.0
 */

@RestController
@RequiredArgsConstructor
@RequestMapping(value = AUTH_FULL_PATH)
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;


    @PostMapping(LOGIN_FULL_PATH)
    public AuthenticationResponseDTO login(@RequestBody AuthenticationRequestDTO requestDto) {
        try {
            String username = requestDto.username();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.password()));
            User user = userService.findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles());


            return new AuthenticationResponseDTO(username, token);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}