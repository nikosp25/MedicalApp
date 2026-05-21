package gr.cf9.MedicalTest.dto.doctor;

public record DoctorAdminReadOnlyDTO(
        Long id,
        Long userId,
        String firstname,
        String lastname,
        String email,
        String licenseNumber,
        Long specialtyId,
        String specialtyName,
        boolean isUserDeleted

) {
}
