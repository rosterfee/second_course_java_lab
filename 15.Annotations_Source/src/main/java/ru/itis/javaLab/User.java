package ru.itis.javaLab;

@HtmlFormAnnotation(method = "post", action = "users")
public class User {

    @HtmlInputAnnotation(name = "nickname", placeholder = "Введите ник")
    private String nickname;

    @HtmlInputAnnotation(type = "email", name = "email", placeholder = "Введите email")
    private String email;

    @HtmlInputAnnotation(type = "password", name = "password", placeholder = "Введите пароль")
    private String password;
}
