package ru.itis.javalab.api.validation.annotations;

import ru.itis.javalab.api.validation.validators.FieldsValueMatchValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FieldsValueMatchValidator.class)
public @interface FieldsValueMatch {

    String message() default "Значения полей не сопадают";
    String field();
    String fieldMatch();

    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        FieldsValueMatch[] value();
    }

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};


}
