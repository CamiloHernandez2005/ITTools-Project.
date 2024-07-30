package com.example.ITTools.infrastructure.controllers.auth;

import com.example.ITTools.application.repos.IAuthRepo;
import com.example.ITTools.domain.ports.in.auth.dtos.LoginDTO;
import com.example.ITTools.domain.ports.in.auth.dtos.SaveUserDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final IAuthRepo iAuthRepo;

    public AuthController(IAuthRepo iAuthRepo) {
        this.iAuthRepo = iAuthRepo;
    }

    @PostMapping("/login")
    public ResponseEntity<String> token(@RequestBody LoginDTO loginDTO) {
        try {
            String token = iAuthRepo.login(loginDTO);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + token);
            return ResponseEntity.ok().headers(headers).body(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials: " + e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody SaveUserDTO user) {
        try {
            iAuthRepo.register(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed: " + e.getMessage());
        }
    }
}
