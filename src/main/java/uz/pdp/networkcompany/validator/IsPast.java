package uz.pdp.networkcompany.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import uz.pdp.networkcompany.validator.impl.IsPastValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IsPastValidator.class)
@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface IsPast {
    String message() default "date must be past";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}
