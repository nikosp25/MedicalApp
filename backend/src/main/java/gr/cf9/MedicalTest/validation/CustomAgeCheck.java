package gr.cf9.MedicalTest.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CustomAgeCheckValidator.class)
public @interface  CustomAgeCheck {

    String message () default "You don't need a doctor, you need a miracle";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
