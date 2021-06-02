package ru.itis.javalab.api.services;

import ru.itis.javalab.api.dtos.UserDto;

public interface RedisService {

    void addTokenToUser(String token, UserDto userDto);

}
