package gr.cf9.MedicalTest.mapper;


import gr.cf9.MedicalTest.dto.UserAdminReadOnlyDTO;
import gr.cf9.MedicalTest.dto.UserInsertDTO;
import gr.cf9.MedicalTest.dto.UserReadOnlyDTO;
import gr.cf9.MedicalTest.dto.UserUpdateDTO;
import gr.cf9.MedicalTest.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserReadOnlyDTO mapToUserReadOnlyDTO(User user) {
        if (user == null) {
            return null;
        }

        return new UserReadOnlyDTO(
                user.getFirstname(),
                user.getLastname(),
                user.getDateOfBirth(),
                user.getEmail()
        );

    }

    public User mapToUserEntity(UserInsertDTO dto) {
        if (dto == null) {
            return null;
        }

        User user = new User();
        user.setFirstname(dto.firstname());
        user.setLastname(dto.lastname());
        user.setEmail(dto.email());
        user.setDateOfBirth(dto.dateOfBirth());
        user.setPassword(dto.password());

        return user;
    }

    public void updateUserFromDTO(UserUpdateDTO dto, User user) {
       if (dto == null || user == null ) {
           return;
        }
       user.setEmail(dto.email());

    }

    public UserAdminReadOnlyDTO mapToUserAdminReadOnlyDTO(User user) {
        if (user == null) {
            return  null;
        }

        return new UserAdminReadOnlyDTO(
                user.getId(),
                user.getUuid(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getDateOfBirth(),
                user.isDeleted()
        );
    }



}
