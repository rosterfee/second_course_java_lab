package ru.itis.javalab.services;

import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.models.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsersService {

    List<User> getAllUsers();

    List<User> getAllUsersByAge(int age);

    Optional<User> getUserByUUID(UUID uuid);

    Optional<User> getUserByLogin(String login);

    List<UserDto> getAllUsers(int page, int size);

    void addUser(UserDto userDto);

}
