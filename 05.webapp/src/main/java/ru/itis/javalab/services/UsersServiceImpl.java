package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UsersRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
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
    public Optional<User> getUserByUUID(UUID uuid) {
        return usersRepository.findByUUID(uuid);
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        return usersRepository.findUserByLogin(login);
    }

    @Override
    public List<UserDto> getAllUsers(int page, int size) {
        return UserDto.from(usersRepository.findAll(page, size));
    }

    @Override
    public void addUser(UserDto userDto) {
        usersRepository.save(User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                    .build());
    }
}
