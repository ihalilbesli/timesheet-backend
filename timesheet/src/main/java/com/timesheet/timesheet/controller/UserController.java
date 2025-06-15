package com.timesheet.timesheet.controller;

import com.timesheet.timesheet.dto.PasswordUpdateRequest;
import com.timesheet.timesheet.model.User;
import com.timesheet.timesheet.repository.UserRepository;
import com.timesheet.timesheet.service.UserService;
import com.timesheet.timesheet.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/timesheet/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUserProfile() {
        return ResponseEntity.ok(userService.getCurrentUser());
    }
    @PutMapping("/me")
    public ResponseEntity<User> updateCurrentUser(@RequestBody User updatedUser) {
        return ResponseEntity.ok(userService.updateCurrentUser(updatedUser));
    }

    @PutMapping("/me/password")
    public ResponseEntity<Map<String, String>> updatePassword(@RequestBody PasswordUpdateRequest request) {
        userService.updatePassword(request.getOldPassword(), request.getNewPassword());
        return ResponseEntity.ok(Map.of("message", "Şifre başarıyla güncellendi."));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }


}
