package uz.pdp.networkcompany.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import uz.pdp.networkcompany.validator.impl.IsOwnNumberValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IsOwnNumberValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsOwnNumber {
    String startCode();
    String message() default "number must start with {startCode}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
