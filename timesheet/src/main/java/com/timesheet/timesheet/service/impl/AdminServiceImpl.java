package com.timesheet.timesheet.service.impl;

import com.timesheet.timesheet.model.Timesheet;
import com.timesheet.timesheet.model.User;
import com.timesheet.timesheet.repository.TimesheetRepository;
import com.timesheet.timesheet.repository.UserRepository;
import com.timesheet.timesheet.service.AdminService;
import com.timesheet.timesheet.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final TimesheetRepository timesheetRepository;

    @Override
    public List<User> searchUsers(String username, String email, LocalDate startDate, LocalDate endDate) {
        User currentUser = SecurityUtil.getCurrentUser(userRepository);
        if (currentUser.getRole() != User.Role.ADMIN) {
            throw new RuntimeException("Sadece admin kullanıcılar kullanıcıları arayabilir.");
        }

        List<User> users;

        if (startDate != null && endDate != null) {
            users = userRepository.findByCreatedAtBetween(startDate, endDate);
        } else {
            users = userRepository.findAll();
        }

        return users.stream()
                .filter(u -> (username == null || u.getUsername().toLowerCase().contains(username.toLowerCase())))
                .filter(u -> (email == null || u.getEmail().toLowerCase().contains(email.toLowerCase())))
                .toList();
    }

    @Override
    public List<Timesheet> getAllTimesheets() {
        User currentUser = SecurityUtil.getCurrentUser(userRepository);
        if (currentUser.getRole() != User.Role.ADMIN) {
            throw new RuntimeException("Sadece admin tüm timesheet verilerine erişebilir.");
        }
        return timesheetRepository.findAll();
    }
}
