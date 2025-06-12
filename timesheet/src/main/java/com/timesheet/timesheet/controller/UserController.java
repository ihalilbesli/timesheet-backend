package com.timesheet.timesheet.controller;

import com.timesheet.timesheet.model.User;
import com.timesheet.timesheet.repository.UserRepository;
import com.timesheet.timesheet.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/timesheet/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUserProfile() {
        User currentUser = SecurityUtil.getCurrentUser(userRepository);
        return ResponseEntity.ok(currentUser);
    }
}
