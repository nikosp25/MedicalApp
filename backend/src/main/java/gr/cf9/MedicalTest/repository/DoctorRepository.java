package gr.cf9.MedicalTest.repository;

import gr.cf9.MedicalTest.model.Doctor;
import gr.cf9.MedicalTest.model.static_data.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {

    List<Doctor> findBySpecialtyAndDeletedFalse(Specialty specialty);

    List<Doctor> findBySpecialty (Specialty specialty);

    Optional<Doctor> findByLicenseNumber(String licenseNumber);

    Optional<Doctor> findByLicenseNumberAndDeletedFalse (String licenseNumber);

    boolean existsByLicenseNumber(String licenseNumber);


    boolean existsByUserId(Long userId);


}
