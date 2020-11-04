package ru.itis.javalab.services;

import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UsersRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UsersServiceImpl implements UsersService {

    private UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public List<User> getAllUsersByAge(int age) {
        return usersRepository.findAllByAge(age);
    }

    @Override
    public Optional<User> getUserByLoginAndPassword(String login, String password) {
        return usersRepository.findByLoginAndPassword(login, password);
    }

    @Override
    public Optional<User> getUserByUUID(UUID uuid) {
        return usersRepository.findByUUID(uuid);
    }

    @Override
    public Optional<String> getUserPasswordByLogin(String login) {
        return usersRepository.findPasswordByLogin(login);
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        return usersRepository.findUserByLogin(login);
    }
}
