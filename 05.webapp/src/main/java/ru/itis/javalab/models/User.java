package ru.itis.javalab.models;

import lombok.*;

import java.util.UUID;

/**
 * 08.10.2020
 * 05. WebApp
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Builder
@EqualsAndHashCode
@ToString
@Data
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String login;
    private String password;
    private UUID uuid;
}

