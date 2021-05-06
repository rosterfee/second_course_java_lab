package ru.itis.javalab.api.services;

import ru.itis.javalab.api.dtos.SignUpFormDto;

public interface SignUpService {

    void signUpUser(SignUpFormDto signUpFormDto);

    boolean userWithSuchEmailExists(String email);

}
