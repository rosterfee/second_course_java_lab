package ru.itis.javalab.impl.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import ru.itis.javalab.api.dtos.JwtTokens;
import ru.itis.javalab.api.dtos.UserDto;
import ru.itis.javalab.impl.entities.RefreshToken;
import ru.itis.javalab.impl.repositories.RefreshTokenRepository;
import ru.itis.javalab.impl.repositories.UsersRepository;

import java.util.Date;
import java.util.UUID;

@Service
public class JwtAuthenticationService {

    @Autowired
    private Environment environment;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    public JwtTokens createTokens(UserDto userDto) {

        Date now = new Date();
        Date accessTokeExpiredAt = new Date(now.getTime() + 1800000);
        Date refreshTokenExpiredAt = new Date(now.getTime() + 2592000000L);

        String accessToken = JWT.create()
                .withSubject(userDto.getId().toString())
                .withClaim("id", userDto.getId())
                .withClaim("email", userDto.getEmail())
                .withClaim("name", userDto.getName())
                .withClaim("role", userDto.getRole().toString())
                .withIssuedAt(now)
                .withExpiresAt(accessTokeExpiredAt)
                .sign(Algorithm.HMAC256("damir_habirovich"));

        RefreshToken refreshToken = RefreshToken.builder()
                .uuid(UUID.randomUUID().toString())
                .userId(userDto.getId())
                .expires_at(refreshTokenExpiredAt)
                    .build();

        refreshTokenRepository.save(refreshToken);

        return JwtTokens.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getUuid())
                    .build();

    }

}
