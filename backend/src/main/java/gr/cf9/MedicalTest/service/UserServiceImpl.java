package gr.cf9.MedicalTest.service;

import gr.cf9.MedicalTest.core.exceptions.EntityAlreadyExistsException;
import gr.cf9.MedicalTest.core.exceptions.EntityNotFoundException;
import gr.cf9.MedicalTest.dto.user.UserAdminReadOnlyDTO;
import gr.cf9.MedicalTest.dto.user.UserInsertDTO;
import gr.cf9.MedicalTest.dto.user.UserReadOnlyDTO;
import gr.cf9.MedicalTest.dto.user.UserUpdateDTO;
import gr.cf9.MedicalTest.mapper.UserMapper;
import gr.cf9.MedicalTest.model.Role;
import gr.cf9.MedicalTest.model.User;
import gr.cf9.MedicalTest.repository.RoleRepository;
import gr.cf9.MedicalTest.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserMapper userMapper;



    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           BCryptPasswordEncoder passwordEncoder,
                           UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }


    @Override
    @Transactional
    public void registerUser(UserInsertDTO dto) {

        if (userRepository.existsByEmail(dto.email())) {
            throw new EntityAlreadyExistsException("Email is already taken");
        }

        Role defaultRole = roleRepository.findByRoleName("USER")
                .orElseThrow(() -> new EntityNotFoundException("Role USER not found"));

        User user = userMapper.mapToUserEntity(dto);
        user.setPassword((passwordEncoder.encode(dto.password())));
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


        userMapper.updateUserFromDTO(dto,existingUser);
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
        User userToReturn = userRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + id + " not found. "));

        return  userMapper.mapToUserReadOnlyDTO(userToReturn);

    }

    @Override
    @Transactional
    public List<UserAdminReadOnlyDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::mapToUserAdminReadOnlyDTO)
                .toList();
    }
}