package ru.home.crypto.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.home.crypto.dto.request.AuthenticationRequestDTO;
import ru.home.crypto.dto.response.AuthenticationResponseDTO;
import ru.home.crypto.entity.security.User;
import ru.home.crypto.repository.UserRepository;
import ru.home.crypto.security.jwt.JwtTokenProvider;
import ru.home.crypto.service.AuthenticationService;

@Service
@Log4j2
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @Lazy
    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }

    @Override
    public AuthenticationResponseDTO authenticateUserAndGenerateToken(AuthenticationRequestDTO requestDTO) {
        try {
            String username = requestDTO.username();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDTO.password()));

            User user = findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles());

           return new AuthenticationResponseDTO(username, token);

        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username);
        log.info("IN findByUsername - user: {} found by username: {}", result, username);
        return result;
    }

}
