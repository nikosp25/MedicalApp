package gr.cf9.MedicalTest.service;

import gr.cf9.MedicalTest.core.exceptions.EntityAlreadyExistsException;
import gr.cf9.MedicalTest.core.exceptions.EntityNotFoundException;
import gr.cf9.MedicalTest.dto.UserInsertDTO;
import gr.cf9.MedicalTest.dto.UserReadOnlyDTO;
import gr.cf9.MedicalTest.dto.UserUpdateDTO;
import gr.cf9.MedicalTest.model.Role;
import gr.cf9.MedicalTest.model.User;
import gr.cf9.MedicalTest.repository.RoleRepository;
import gr.cf9.MedicalTest.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements IUserService {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    @Transactional
    public void registerUser(UserInsertDTO dto) {

        if (userRepository.existsByEmail(dto.email())) {
            throw new EntityAlreadyExistsException("Email is already taken");
        }

        Role defaultRole = roleRepository.findByRoleName("USER")
                .orElseThrow(() -> new EntityNotFoundException("Role USER not found"));

        User user = new User();
        user.setFirstname(dto.firstname());
        user.setLastname(dto.lastname());
        user.setEmail(dto.email());
        user.setDateOfBirth(dto.dateOfBirth());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.getRoles().add(defaultRole);

        userRepository.save(user);
    }


    @Override
    @Transactional
    public void updateUser(UserUpdateDTO dto)  {
        // 1. Fetch the user using the built-in findById from JpaRepository
        User existingUser = userRepository.findById(dto.id())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + dto.id()));


        if (!existingUser.getEmail().equals(dto.email())) {
            if (userRepository.existsByEmail(dto.email())) {
                throw new EntityAlreadyExistsException("Email " + dto.email() + " is already taken by another account.");
            }
        }


        existingUser.setEmail(dto.email());



        userRepository.save(existingUser);
    }

    @Override
    @Transactional
    public void deleteUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User with " + email + " not found"));
        user.softDelete();
        userRepository.save(user);

    }


    @Override
    @Transactional
    public UserReadOnlyDTO getUserById(Long id) {
        User userToReturn = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + id + " not found. "));

        return  new UserReadOnlyDTO(
                userToReturn.getFirstname(),
                userToReturn.getLastname(),
                userToReturn.getDateOfBirth(),
                userToReturn.getEmail()
        );

    }
}