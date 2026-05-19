package gr.cf9.MedicalTest.repository;

import gr.cf9.MedicalTest.model.static_data.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpecialtyRepository extends JpaRepository <Specialty,Long> {

    Optional<Specialty> findByDoctorSpecialty(String doctorSpecialty);
}
