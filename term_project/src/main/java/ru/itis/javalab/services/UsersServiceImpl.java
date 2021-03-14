package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.RegistrationForm;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UsersRepository;

import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public Optional<User> getUserByEmail(String email) {
        return usersRepository.getUserByEmail(email);
    }

    @Override
    public Optional<User> getUserByConfirmCode(String code) {
        return usersRepository.getUserByConfirmCode(code);
    }

    @Override
    public void updateUser(User user) {
        usersRepository.save(user);
    }

}
