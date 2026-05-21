package gr.cf9.MedicalTest.controller;


import gr.cf9.MedicalTest.dto.user.UserInsertDTO;
import gr.cf9.MedicalTest.dto.user.UserReadOnlyDTO;
import gr.cf9.MedicalTest.dto.user.UserUpdateDTO;
import gr.cf9.MedicalTest.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser (
            @Valid
            @RequestBody
            UserInsertDTO dto
    ) {
        userService.registerUser(dto);

        return ResponseEntity.ok(
                "Success! User with firstname :  " + dto.firstname() + " and lastname : " + dto.lastname() + " registered successfully"
        );
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUser (
            @Valid
            @RequestBody
            UserUpdateDTO dto
    ) {
        userService.updateUser(dto);

        return ResponseEntity.ok(
                "User successfully updated."
        );
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser (
            @Valid
            @RequestParam
            String email
    ) {
        userService.deleteUserByEmail(email);

        return ResponseEntity.ok("User successfully deleted.");
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<UserReadOnlyDTO> getUser
            (@PathVariable Long id) {

        UserReadOnlyDTO readOnlyDTO = userService.getUserById(id);

        return ResponseEntity.ok(readOnlyDTO);


    }



}
