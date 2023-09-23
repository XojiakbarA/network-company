package uz.pdp.networkcompany.validator.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import uz.pdp.networkcompany.validator.IsPast;

import java.util.Date;

public class IsPastValidator implements ConstraintValidator<IsPast, Date> {
    @Override
    public void initialize(IsPast constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext) {
        if (date == null) return true;
        return date.before(new Date());
    }
}
