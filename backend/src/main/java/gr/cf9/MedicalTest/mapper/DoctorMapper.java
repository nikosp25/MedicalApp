package gr.cf9.MedicalTest.mapper;


import gr.cf9.MedicalTest.dto.doctor.DoctorAdminReadOnlyDTO;
import gr.cf9.MedicalTest.dto.doctor.DoctorInsertDTO;
import gr.cf9.MedicalTest.dto.doctor.DoctorReadOnlyDTO;
import gr.cf9.MedicalTest.model.Doctor;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {
    public Doctor mapToDoctorEntity(DoctorInsertDTO dto) {
        if (dto == null) {
            return null;
        }

        Doctor doctor = new Doctor();
        doctor.setLicenseNumber(dto.licenseNumber());
        return doctor;
    }

    public DoctorReadOnlyDTO mapToDoctorReadOnlyDTO(Doctor doctor) {
        if (doctor == null) {
            return null;
        }
        return new DoctorReadOnlyDTO(
                doctor.getUser().getFirstname(),
                doctor.getUser().getLastname(),
                doctor.getSpecialty().getDoctorSpecialty(),
                doctor.getLicenseNumber()
        );
    }

    public DoctorAdminReadOnlyDTO mapToDoctorAdminReadOnlyDTO(Doctor doctor) {
        if (doctor == null) return null;

        return new DoctorAdminReadOnlyDTO(
                doctor.getId(),
                doctor.getUser().getId(),
                doctor.getUser().getFirstname(),
                doctor.getUser().getLastname(),
                doctor.getUser().getEmail(),
                doctor.getLicenseNumber(),
                doctor.getSpecialty().getId(),
                doctor.getSpecialty().getDoctorSpecialty(),
                doctor.getUser().isDeleted()
        );
    }
}
