package ru.itis.javalab.services;

import ru.itis.javalab.models.User;

import java.util.Optional;

public interface UsersService {

    Optional<User> getUserByEmail(String email);

    Optional<User> getUserByConfirmCode(String code);

    void updateUser(User user);

}
