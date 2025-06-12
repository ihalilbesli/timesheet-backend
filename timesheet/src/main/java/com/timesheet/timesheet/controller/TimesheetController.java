package com.timesheet.timesheet.controller;

import com.timesheet.timesheet.model.Timesheet;
import com.timesheet.timesheet.service.TimesheetService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/timesheet/timesheets/users")
@RequiredArgsConstructor
public class TimesheetController {

    private final TimesheetService timesheetService;

    @PostMapping
    public ResponseEntity<Timesheet> create(@RequestBody Timesheet timesheet) {
        return ResponseEntity.ok(timesheetService.createTimesheet(timesheet));
    }

    @GetMapping
    public ResponseEntity<List<Timesheet>> getOwnTimesheets() {
        return ResponseEntity.ok(timesheetService.getTimesheetsByCurrentUser());
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Timesheet>> getByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end
    ) {
        return ResponseEntity.ok(timesheetService.getTimesheetsByCurrentUserBetweenDates(start, end));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Timesheet> updateTimesheet(
            @PathVariable Long id,
            @RequestBody Timesheet updated
    ) {
        return ResponseEntity.ok(timesheetService.updateTimesheet(id, updated));
    }
    @GetMapping("/admin/user/{userId}")
    public ResponseEntity<List<Timesheet>> getTimesheetsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(timesheetService.getTimesheetsByUserId(userId));
    }
    @GetMapping("/all")
    public ResponseEntity<List<Timesheet>> getAllTimesheets() {
        return ResponseEntity.ok(timesheetService.getAllTimesheets());
    }
}
