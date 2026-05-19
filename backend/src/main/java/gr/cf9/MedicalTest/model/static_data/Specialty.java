package gr.cf9.MedicalTest.model.static_data;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "specialties")

public class Specialty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "doctor_specialty", nullable = false, unique = true)
    private String doctorSpecialty;

    public Specialty (String doctorSpecialty) {
        this.doctorSpecialty = doctorSpecialty;
    }
}
