package ru.itis.javaLab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javaLab.models.User;
import ru.itis.javaLab.repositories.UsersRepository;

import java.util.Optional;

@Service
public class UsersService {

    UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Optional<User> getUserByLoginAndPassword(String login, String password) {
        return usersRepository.findUserByLoginAndPassword(login, password);
    }

    public void deleteUserById(long id) {
        usersRepository.deleteById(id);
    }

}
