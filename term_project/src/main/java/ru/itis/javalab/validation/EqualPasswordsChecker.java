package ru.itis.javalab.validation;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EqualPasswordsChecker implements ConstraintValidator<EqualPasswords, Object> {

    private String firstPassword;
    private String secondPassword;

    @Override
    public void initialize(EqualPasswords constraintAnnotation) {
        firstPassword = constraintAnnotation.firstPassword();
        secondPassword = constraintAnnotation.secondPassword();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {

        Object first = new BeanWrapperImpl(o).getPropertyValue(firstPassword);
        Object second = new BeanWrapperImpl(o).getPropertyValue(secondPassword);

        assert first != null;
        return first.equals(second);

    }
    
}
