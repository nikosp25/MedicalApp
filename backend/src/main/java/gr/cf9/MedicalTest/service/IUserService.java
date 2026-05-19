package gr.cf9.MedicalTest.service;

import gr.cf9.MedicalTest.dto.UserInsertDTO;
import gr.cf9.MedicalTest.dto.UserUpdateDTO;

public interface IUserService {


    void registerUser(UserInsertDTO dto);


    void updateUser(UserUpdateDTO dto);

    void deleteUserByEmail(String email);
}
