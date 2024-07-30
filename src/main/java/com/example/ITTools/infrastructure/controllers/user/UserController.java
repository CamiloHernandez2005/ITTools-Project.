package com.example.ITTools.infrastructure.controllers.user;

import com.example.ITTools.application.usecases.GetUsersUseCaseImpl;
import com.example.ITTools.application.usecases.UpdateUserUseCaseImpl;
import com.example.ITTools.domain.ports.in.auth.dtos.SaveUserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final GetUsersUseCaseImpl getUsersUseCaseImpl;
    private final UpdateUserUseCaseImpl updateUserUseCaseImpl;

    public UserController(GetUsersUseCaseImpl getUsersUseCaseImpl, UpdateUserUseCaseImpl updateUserUseCaseImpl) {
        this.getUsersUseCaseImpl = getUsersUseCaseImpl;
        this.updateUserUseCaseImpl = updateUserUseCaseImpl;
    }

    @GetMapping
    public List<SaveUserDTO> getUsers() {
        return getUsersUseCaseImpl.getAllUsers();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaveUserDTO> updateUser(
            @PathVariable("id") UUID id,
            @RequestBody SaveUserDTO saveUserDTO) {
        try {
            SaveUserDTO updatedUser = updateUserUseCaseImpl.updateUser(id, saveUserDTO);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
