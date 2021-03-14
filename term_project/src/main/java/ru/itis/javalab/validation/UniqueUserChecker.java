package ru.itis.javalab.validation;

import org.springframework.beans.factory.annotation.Autowired;
import ru.itis.javalab.models.User;
import ru.itis.javalab.services.UsersService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class UniqueUserChecker implements ConstraintValidator<UniqueUser, String> {

   @Autowired
   UsersService usersService;

   public boolean isValid(String value, ConstraintValidatorContext context) {
      Optional<User> optionalUser = usersService.getUserByEmail(value);
      return !optionalUser.isPresent();
   }
}
