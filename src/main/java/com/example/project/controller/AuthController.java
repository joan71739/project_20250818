package com.example.project.controller;

import com.example.project.model.JwtResponse;
import com.example.project.util.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class AuthController {

    private final JwtUtil jwtUtil;

    @Value("${spring.security.user.name}")
    private String configuredUsername;

    @Value("${spring.security.user.password}")
    private String configuredPassword;

    // 建構子注入（推薦）
    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> payload) {
        String username = payload.get("username");
        String password = payload.get("password");

        if (configuredUsername.equals(username) && configuredPassword.equals(password)) {
            String token = jwtUtil.generateToken(username);
            return ResponseEntity.ok(new JwtResponse(token));
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}
