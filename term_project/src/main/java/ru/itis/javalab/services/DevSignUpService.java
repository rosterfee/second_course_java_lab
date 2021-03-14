package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.RegistrationForm;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UsersRepository;

@Service
@Profile("dev")
public class DevSignUpService implements SignUpService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void signUpUser(RegistrationForm form) {
        User user = User.builder()
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .email(form.getEmail())
                .password(passwordEncoder.encode(form.getPassword1()))
                .avatar("default.png")
                .confirmCode(null)
                    .build();
        usersRepository.save(user);
    }

}

