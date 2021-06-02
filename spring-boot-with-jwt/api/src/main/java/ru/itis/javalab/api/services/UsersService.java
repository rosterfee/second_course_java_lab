package ru.itis.javalab.api.services;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import ru.itis.javalab.api.dtos.UserDto;

import java.util.Optional;

public interface UsersService {

    Optional<UserDto> getUserByEmailAndPassword(String email, String password);

    UserDto getById(long id);

    void banUser(Long id);

    void setRedisId(Long userId, Long redisId);

}
