package ru.itis.javalab.api.validation.validators;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import ru.itis.javalab.api.validation.annotations.FieldsValueMatch;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldsValueMatchValidator implements
        ConstraintValidator<FieldsValueMatch, Object> {

    private String field;
    private String fieldMatch;

    @Override
    public void initialize(FieldsValueMatch constraintAnnotation) {
        field = constraintAnnotation.field();
        fieldMatch = constraintAnnotation.fieldMatch();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {

        BeanWrapper wrapper = new BeanWrapperImpl(value);
        Object fieldValue = wrapper.getPropertyValue(field);
        Object fieldMatchValue = wrapper.getPropertyValue(fieldMatch);

        if (fieldValue != null) {
            return fieldValue.equals(fieldMatchValue);
        } else {
            return fieldMatchValue == null;
        }
    }
}
