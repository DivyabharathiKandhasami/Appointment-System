package com.example.system.Controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.system.DTO.AuthRequest;
import com.example.system.DTO.AuthResponse;
import com.example.system.Entity.Role;
import com.example.system.Entity.User;
import com.example.system.Repository.RoleRepository;
import com.example.system.Repository.UserRepository;
import com.example.system.jwtUtil.jwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private jwtUtil jwtUtil;
    @Autowired private UserRepository userRepo;
    @Autowired private RoleRepository roleRepo;
    @Autowired private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepo.findByName("ROLE_USER");
        if (role == null) {
            throw new RuntimeException("ROLE_USER not found in database");
        }

        user.setRoles(Set.of(role));
        userRepo.save(user);
        return "User registered successfully!";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        System.out.println("🔐 Login attempt for: " + request.getUsername());

        // Step 1: Check if user exists in database
        User user = userRepo.findByUsername(request.getUsername());
        if (user == null) {
            return ResponseEntity.status(404).body("❌ User not found in database. Please register first.");
        }

        try {
            // Step 2: Authenticate username + password
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            // Step 3: Generate token
            String token = jwtUtil.generateToken(request.getUsername());
            return ResponseEntity.ok(new AuthResponse(token));

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Invalid password.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Something went wrong.");
        }
    }


}
