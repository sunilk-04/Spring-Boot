package com.prominentpixel.springbootsecurity.validation;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MatchValidator implements ConstraintValidator<Match, Object> {

    private String first;
    private String second;
    private String message;

    @Override
    public void initialize(Match constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.first = constraintAnnotation.first();
        this.second = constraintAnnotation.second();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext context) throws NullPointerException {
        boolean flag = true;
        try {
            final Object firstObj = new BeanWrapperImpl(o).getPropertyValue(this.first);
            final Object secondObj = new BeanWrapperImpl(o).getPropertyValue(this.second);
            flag = firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        if (!flag) {
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(this.first)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }
        return flag;
    }
}
