package gr.cf9.MedicalTest.service;

import gr.cf9.MedicalTest.dto.user.UserAdminReadOnlyDTO;
import gr.cf9.MedicalTest.dto.user.UserInsertDTO;
import gr.cf9.MedicalTest.dto.user.UserReadOnlyDTO;
import gr.cf9.MedicalTest.dto.user.UserUpdateDTO;

import java.util.List;

public interface IUserService {


    void registerUser(UserInsertDTO dto);


    void updateUser(UserUpdateDTO dto);

    void deleteUserByEmail(String email);

    UserReadOnlyDTO getUserById(Long id);

    List<UserAdminReadOnlyDTO> getAllUsers();
}
