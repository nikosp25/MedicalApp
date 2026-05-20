package gr.cf9.MedicalTest.dto;

import java.time.LocalDate;
import java.util.UUID;

public record UserAdminReadOnlyDTO(
        Long id,
        UUID uuid,
        String firstname,
        String lastname,
        String email,
        LocalDate dateOfBirth,
        Boolean isDeleted
) {
}
