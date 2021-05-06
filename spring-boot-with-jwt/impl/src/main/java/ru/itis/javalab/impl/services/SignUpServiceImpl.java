package ru.itis.javalab.impl.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.javalab.api.dtos.SignUpFormDto;
import ru.itis.javalab.api.enums.Role;
import ru.itis.javalab.api.services.SignUpService;
import ru.itis.javalab.impl.entities.User;
import ru.itis.javalab.impl.repositories.UsersRepository;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void signUpUser(SignUpFormDto signUpFormDto) {

        User user = User.builder()
                .email(signUpFormDto.getEmail())
                .name(signUpFormDto.getName())
                .hashedPassword(passwordEncoder.encode(signUpFormDto.getPassword()))
                .role(Role.USER)
                    .build();

        usersRepository.save(user);
    }

    @Override
    public boolean userWithSuchEmailExists(String email) {
        return usersRepository.existsByEmail(email);
    }

}
