package uz.pdp.networkcompany.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import uz.pdp.networkcompany.validator.impl.IsValidEnumValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IsValidEnumValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsValidEnum {
    Class<? extends Enum<?>> enumClazz();
    String message() default "must be any of enum {enumClazz}";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}
