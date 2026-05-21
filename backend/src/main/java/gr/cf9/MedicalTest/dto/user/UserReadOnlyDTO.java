package gr.cf9.MedicalTest.dto.user;


import java.time.LocalDate;


public record UserReadOnlyDTO(
        String firstname,

        String lastname,

        LocalDate dateOfBirth,

        String email


) {
}
