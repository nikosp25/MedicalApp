package gr.cf9.MedicalTest.config;

import gr.cf9.MedicalTest.model.Role;
import gr.cf9.MedicalTest.model.static_data.Specialty;
import gr.cf9.MedicalTest.repository.RoleRepository;
import gr.cf9.MedicalTest.repository.SpecialtyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final SpecialtyRepository specialtyRepository;

    public DataSeeder(RoleRepository roleRepository, SpecialtyRepository specialtyRepository) {
        this.roleRepository = roleRepository;
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // set since the roles and specialties are unique.
        Set<String> roles = Set.of("USER", "DOCTOR", "ADMIN", "PATIENT");
        for (String roleName : roles) {
            if (roleRepository.findByRoleName(roleName).isEmpty()) {
                Role role = new Role();
                role.setRoleName(roleName);
                roleRepository.save(role);
            }
        }

        Set<String> specialties = Set.of("SURGEON", "CARDIOLOGIST", "PEDIATRICIAN", "DERMATOLOGIST", "NEUROLOGIST");
        for (String specialtyName : specialties) {
            if (specialtyRepository.findByDoctorSpecialty(specialtyName).isEmpty()) {
                specialtyRepository.save(new Specialty(specialtyName));
            }
        }
    }
}