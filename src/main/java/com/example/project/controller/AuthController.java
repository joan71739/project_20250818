package com.example.project.controller;

import com.example.project.model.JwtResponse;
import com.example.project.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AuthController {

    private final JwtUtil jwtUtil;

    // 建構子注入（推薦）
    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> payload) {
        String username = payload.get("username");
        String password = payload.get("password");

        // 這裡可以添加用戶認證邏輯
        // 假設認證成功，返回一個成功的響應\

        System.out.println("驗證帳號密碼: " + username + ", " + password);
        if ("user".equals(username) && "0000".equals(password)) {
            String token = jwtUtil.generateToken(username);
            System.out.println(new JwtResponse(token));
            return ResponseEntity.ok(new JwtResponse(token));
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}
