package ru.itis.javalab.services;

import ru.itis.javalab.models.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsersService {
    List<User> getAllUsers();

    List<User> getAllUsersByAge(int age);

    Optional<User> getUserByLoginAndPassword(String login, String password);

    Optional<User> getUserByUUID(UUID uuid);

    Optional<String> getUserPasswordByLogin(String login);

    Optional<User> getUserByLogin(String login);
}
