package gr.cf9.MedicalTest.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;

public class CustomAgeCheckValidator implements ConstraintValidator<CustomAgeCheck,LocalDate> {
    public boolean isValid (LocalDate dateOfBirth, ConstraintValidatorContext context) {
        if (dateOfBirth == null) return true;

        int age = Period.between(dateOfBirth,LocalDate.now()).getYears();
        if (age >= 150) return false;
        return true;
        }

    }



