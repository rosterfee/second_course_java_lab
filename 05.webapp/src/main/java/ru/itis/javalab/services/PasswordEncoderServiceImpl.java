package ru.itis.javalab.services;

import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderServiceImpl implements PasswordEncoderService {

    PasswordEncoder passwordEncoder;

    public PasswordEncoderServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encode(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public boolean matches(String password, String hashedPassword) {
        return passwordEncoder.matches(password, hashedPassword);
    }
}
