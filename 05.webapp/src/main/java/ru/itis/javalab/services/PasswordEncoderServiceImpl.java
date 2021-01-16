package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncoderServiceImpl implements PasswordEncoderService {

    @Autowired
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
