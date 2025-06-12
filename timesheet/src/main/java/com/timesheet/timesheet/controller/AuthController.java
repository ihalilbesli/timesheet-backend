package com.timesheet.timesheet.controller;

import com.timesheet.timesheet.dto.LoginRequest;
import com.timesheet.timesheet.dto.RegisterRequest;
import com.timesheet.timesheet.model.User;
import com.timesheet.timesheet.service.UserService;
import com.timesheet.timesheet.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/timesheet/auth")
@RequiredArgsConstructor

public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder; // encoder burada

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
        if (userService.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest().body("Kullanıcı adı zaten var.");
        }

        if (userService.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body("E-posta zaten kullanılıyor.");
        }

        if (!isValidPassword(request.getPassword())) {
            return ResponseEntity.badRequest().body("Zayıf şifre.");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(User.Role.USER);

        return ResponseEntity.ok(userService.save(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Optional<User> optionalUser = userService.findByUsername(request.getUsername());

        if (optionalUser.isEmpty() ||
                !passwordEncoder.matches(request.getPassword(), optionalUser.get().getPassword())) {
            return ResponseEntity.status(401).body("Geçersiz kullanıcı adı veya şifre.");
        }

        String token = jwtUtil.generateToken(optionalUser.get());

        // Token'ı JSON olarak döndürüyoruz
        return ResponseEntity.ok(Map.of("token", token));
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 8 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*[a-z].*") &&
                password.matches(".*[^a-zA-Z0-9].*");
    }
}
