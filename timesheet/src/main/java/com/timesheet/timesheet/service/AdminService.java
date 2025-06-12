package com.timesheet.timesheet.service;

import com.timesheet.timesheet.model.Timesheet;
import com.timesheet.timesheet.model.User;

import java.time.LocalDate;
import java.util.List;

public interface AdminService {

    List<User> searchUsers(String username, String email, LocalDate startDate, LocalDate endDate);

    List<Timesheet> getAllTimesheets(); // tüm kayıtları getirir
}
