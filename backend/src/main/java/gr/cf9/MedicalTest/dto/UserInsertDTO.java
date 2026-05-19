package gr.cf9.MedicalTest.dto;

import gr.cf9.MedicalTest.validation.CustomAgeCheck;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UserInsertDTO(

        @NotEmpty //cant be null or empty
        @Size(min = 2, max = 30, message = "firstname must be between 2 and 30 characters.")
        String firstname,

        @NotEmpty
        @Size(min = 2, max = 30, message = "lastname must be between 2 and 30 characters.")
        String lastname,



        @NotEmpty
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
                message = "Password must contain uppercase, lowercase, number, special character and be at least 8 characters long"
        )
        String password,

        @Email
        @NotEmpty(message = "email cannot be empty")
        String email,

        @NotNull(message = "date of birth is mandatory")
        @CustomAgeCheck
        LocalDate dateOfBirth




) {
}
