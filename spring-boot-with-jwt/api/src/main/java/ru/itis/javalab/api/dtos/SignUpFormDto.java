package ru.itis.javalab.api.dtos;

import lombok.Data;
import ru.itis.javalab.api.validation.annotations.FieldsValueMatch;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@FieldsValueMatch(field = "password",
                fieldMatch = "verifyPassword",
                message = "Пароли не совпадают")
public class SignUpFormDto {

    @Email(message = "Формат адреса электронной почты неверен")
    @NotEmpty
    private String email;

    @NotEmpty
    @Size(max = 50, message = "Максимальная блина поля: 50 символов")
    private String name;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$",
    message = "Пароль должен содеражть латиницу обоих регистров, хотя бы одну цифру и " +
        "иметь длину не менее 8 указанных символов")
    private String password;

    private String verifyPassword;

}
