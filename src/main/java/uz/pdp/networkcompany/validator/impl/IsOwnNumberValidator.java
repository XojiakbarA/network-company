package uz.pdp.networkcompany.validator.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import uz.pdp.networkcompany.validator.IsOwnNumber;

public class IsOwnNumberValidator implements ConstraintValidator<IsOwnNumber, Long> {
    private String startCode;

    @Override
    public void initialize(IsOwnNumber constraintAnnotation) {
        this.startCode = constraintAnnotation.startCode();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        if (aLong == null) return false;
        return aLong.toString().startsWith(startCode);
    }
}
