package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.RegistrationForm;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UsersRepository;
import ru.itis.javalab.utils.ConfirmMailGenerator;
import ru.itis.javalab.utils.EmailSender;

import java.util.UUID;

@Service
@Profile("prod")
public class ProdSignUpService implements SignUpService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ConfirmMailGenerator confirmMailGenerator;

    @Autowired
    private EmailSender emailSender;

    @Override
    public void signUpUser(RegistrationForm form) {

        String confirmCode = UUID.randomUUID().toString();
        String email = form.getEmail();

        User user = User.builder()
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .email(email)
                .password(passwordEncoder.encode(form.getPassword1()))
                .avatar("default.png")
                .confirmCode(confirmCode)
                .build();
        usersRepository.save(user);

        String mail = confirmMailGenerator.getConfirmMail(confirmCode);
        emailSender.sendMail(email,"Регистрация", mail);

    }
}
