package gr.cf9.MedicalTest.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


public record UserReadOnlyDTO(
        String firstname,

        String lastname,

        LocalDate dateOfBirth,

        String email


) {
}
