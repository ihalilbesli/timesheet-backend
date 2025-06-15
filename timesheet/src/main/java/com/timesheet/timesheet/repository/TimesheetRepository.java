package com.timesheet.timesheet.repository;

import com.timesheet.timesheet.model.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {

    List<Timesheet> findByUserId(Long userId);

    List<Timesheet> findByUserIdAndDateBetween(Long userId, LocalDate startDate, LocalDate endDate);

    List<Timesheet> findByUserIdAndDate(Long userId, LocalDate date);

    void deleteById(Long id);

}