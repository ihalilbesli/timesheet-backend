package com.timesheet.timesheet.service;

import com.timesheet.timesheet.model.Timesheet;

import java.time.LocalDate;
import java.util.List;

public interface TimesheetService {

    Timesheet createTimesheet(Timesheet timesheet);

    List<Timesheet> getTimesheetsByCurrentUser();

    List<Timesheet> getTimesheetsByCurrentUserBetweenDates(LocalDate startDate, LocalDate endDate);

    Timesheet updateTimesheet(Long timesheetId, Timesheet updatedTimesheet);

    List<Timesheet> getAllTimesheets();
    List<Timesheet> getTimesheetsByUserId(Long userId);

}
