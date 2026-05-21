package gr.cf9.MedicalTest.controller;


import gr.cf9.MedicalTest.dto.user.UserAdminReadOnlyDTO;
import gr.cf9.MedicalTest.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")

public class AdminController {
    private final IUserService userService;

    public AdminController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserAdminReadOnlyDTO>> getAllUsers() {
        List<UserAdminReadOnlyDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

}
