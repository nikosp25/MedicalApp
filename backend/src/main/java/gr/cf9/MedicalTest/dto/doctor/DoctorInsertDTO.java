package gr.cf9.MedicalTest.dto.doctor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DoctorInsertDTO(
        @NotNull(message = "User ID is required")
        Long userId,

        @NotBlank(message = "License number is required")
        @Size(min = 5, max = 5, message = "License number must be exactly 5 characters long")
        String licenseNumber,

        @NotNull(message = "Specialty ID is required")
        Long specialtyId
) {}