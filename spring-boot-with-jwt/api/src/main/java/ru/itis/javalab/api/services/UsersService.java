package ru.itis.javalab.api.services;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import ru.itis.javalab.api.dtos.UserDto;
import ru.itis.javalab.api.exceptions.ApiAuthenticationException;

import java.util.Optional;

public interface UsersService {

    Optional<UserDto> getUserByEmailAndPassword(String email, String password);

    UserDto getById(long id);

}
