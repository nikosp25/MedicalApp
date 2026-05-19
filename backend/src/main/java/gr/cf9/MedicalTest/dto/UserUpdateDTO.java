package gr.cf9.MedicalTest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UserUpdateDTO(

        @NotNull(message = "User ID is mandatory")
        Long id,

        @NotBlank(message = "Email cannot be blank")
        @Email(message = "invalid email format")
        String email


) {}



