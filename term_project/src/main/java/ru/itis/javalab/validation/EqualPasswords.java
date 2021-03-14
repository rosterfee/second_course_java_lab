package ru.itis.javalab.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EqualPasswordsChecker.class)
public @interface EqualPasswords {

    String firstPassword();
    String secondPassword();

    String message() default "{errors.diff_passwords}";

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        EqualPasswords[] value();
    }


    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};


}
