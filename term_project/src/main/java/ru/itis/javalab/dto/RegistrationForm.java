package ru.itis.javalab.dto;

import lombok.Data;
import ru.itis.javalab.validation.EqualPasswords;
import ru.itis.javalab.validation.UniqueUser;
import ru.itis.javalab.validation.ValidPassword;

import javax.validation.constraints.Email;

@Data
@EqualPasswords(firstPassword = "password1", secondPassword = "password2")
public class RegistrationForm {

    private String firstName;
    private String lastName;

    @Email(message = "{errors.wrong_email}")
    @UniqueUser(message = "{errors.non_unique_user}")
    private String email;

    @ValidPassword(message = "{errors.wrong_password}")
    private String password1;

    private String password2;

}
