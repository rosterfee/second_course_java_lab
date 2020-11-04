package ru.itis.javalab.services;

public interface PasswordEncoderService {

    String encode(String password);

    boolean matches(String password, String hashedPassword);

}
