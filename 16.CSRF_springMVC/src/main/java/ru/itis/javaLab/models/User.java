package ru.itis.javaLab.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    private long id;
    private String login;
    private String password;

}
