package gr.cf9.MedicalTest.repository;

import gr.cf9.MedicalTest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByEmail(String email);

    Optional<User> findByEmailAndDeletedFalse(String email);

    Optional<User> findByEmail(String email);

    List<User> findByDateOfBirthAndDeletedFalse (LocalDate dateOfBirth);

    List<User> findByDateOfBirth (LocalDate dateOfBirth);
}
