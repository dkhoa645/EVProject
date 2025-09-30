package com.swp391.evproject.controller;

import com.swp391.evproject.model.LoginRequest;
import com.swp391.evproject.security.JWTUtils;
import com.swp391.evproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        try {
            System.out.println("Login attempt for username: " + loginRequest.getUsername());

            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            System.out.println("Authentication successful for: " + loginRequest.getUsername());

            String token = jwtUtils.generateToken((UserDetails) auth.getPrincipal());

            System.out.println("JWT token generated successfully");

            return ResponseEntity.ok(Map.of(
                    "token", token,
                    "type", "Bearer",
                    "username", loginRequest.getUsername()
            ));

        } catch (Exception e) {
            System.out.println("Login failed for username: " + loginRequest.getUsername() +
                    ", Error: " + e.getMessage());

            return ResponseEntity.status(401)
                    .body(Map.of(
                            "error", "Authentication failed",
                            "message", "Invalid username or password"
                    ));
        }
    }

    // Test endpoint to verify the configuration
    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok(Map.of("message", "Public endpoint working"));
    }


//    @PostMapping()
//    public ResponseEntity

}
