package com.timesheet.timesheet.service.impl;

import com.timesheet.timesheet.model.Timesheet;
import com.timesheet.timesheet.model.User;
import com.timesheet.timesheet.repository.TimesheetRepository;
import com.timesheet.timesheet.repository.UserRepository;
import com.timesheet.timesheet.service.TimesheetService;
import com.timesheet.timesheet.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TimesheetServiceImpl implements TimesheetService {

    private final TimesheetRepository timesheetRepository;
    private final UserRepository userRepository;

    @Override
    public Timesheet createTimesheet(Timesheet timesheet) {
        User currentUser = SecurityUtil.getCurrentUser(userRepository);
        timesheet.setUser(currentUser);
        return timesheetRepository.save(timesheet);
    }

    @Override
    public List<Timesheet> getTimesheetsByCurrentUser() {
        User currentUser = SecurityUtil.getCurrentUser(userRepository);
        return timesheetRepository.findByUserId(currentUser.getId());
    }

    @Override
    public List<Timesheet> getTimesheetsByCurrentUserBetweenDates(LocalDate startDate, LocalDate endDate) {
        User currentUser = SecurityUtil.getCurrentUser(userRepository);
        return timesheetRepository.findByUserIdAndDateBetween(currentUser.getId(), startDate, endDate);
    }

    @Override
    public Timesheet updateTimesheet(Long timesheetId, Timesheet updatedTimesheet) {
        User currentUser = SecurityUtil.getCurrentUser(userRepository);

        Timesheet existing = timesheetRepository.findById(timesheetId)
                .orElseThrow(() -> new RuntimeException("Timesheet bulunamadı."));

        // Sadece kendi kaydını güncelleyebilir
        if (!existing.getUser().getId().equals(currentUser.getId()) &&
                currentUser.getRole() != User.Role.ADMIN) {
            throw new RuntimeException("Bu timesheet kaydını güncelleme yetkiniz yok.");
        }

        existing.setDate(updatedTimesheet.getDate());
        existing.setStartTime(updatedTimesheet.getStartTime());
        existing.setEndTime(updatedTimesheet.getEndTime());
        existing.setDescription(updatedTimesheet.getDescription());

        return timesheetRepository.save(existing);
    }

    @Override
    public List<Timesheet> getAllTimesheets() {
        User currentUser = SecurityUtil.getCurrentUser(userRepository);

        if (currentUser.getRole() != User.Role.ADMIN) {
            throw new RuntimeException("Sadece admin tüm kayıtları görebilir.");
        }

        return timesheetRepository.findAll();
    }
}
