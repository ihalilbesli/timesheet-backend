package com.timesheet.timesheet.controller;

import com.timesheet.timesheet.model.Timesheet;
import com.timesheet.timesheet.model.User;
import com.timesheet.timesheet.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/timesheet/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;


    @GetMapping("/users")
    public ResponseEntity<List<User>> searchUsers(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        return ResponseEntity.ok(adminService.searchUsers(username, email, startDate, endDate));
    }

    @GetMapping("/timesheets")
    public ResponseEntity<List<Timesheet>> getAllTimesheets() {
        return ResponseEntity.ok(adminService.getAllTimesheets());
    }
}
