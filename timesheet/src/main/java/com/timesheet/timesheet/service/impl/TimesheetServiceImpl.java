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
        System.out.println("⏳ [createTimesheet] Başlatıldı...");

        Long userId = SecurityUtil.getCurrentUserId();
        System.out.println("🔑 [createTimesheet] currentUserId: " + userId);

        // Gerçek user objesini veritabanından çek
        User currentUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

        System.out.println("📌 [createTimesheet] DB'den user alındı. Email: " + currentUser.getEmail());

        // User setle
        timesheet.setUser(currentUser);

        System.out.println("📅 Tarih: " + timesheet.getDate());
        System.out.println("⏰ Başlangıç: " + timesheet.getStartTime());
        System.out.println("⏰ Bitiş: " + timesheet.getEndTime());
        System.out.println("📝 Açıklama: " + timesheet.getDescription());

        System.out.println("💾 Timesheet kaydediliyor...");
        Timesheet saved = timesheetRepository.save(timesheet);

        System.out.println("✅ [createTimesheet] Kayıt başarılı! ID: " + saved.getId());
        return saved;
    }


    @Override
    public List<Timesheet> getTimesheetsByCurrentUser() {
        System.out.println("📥 [getTimesheetsByCurrentUser] Çağrıldı.");
        User currentUser = SecurityUtil.getCurrentUser(userRepository);
        System.out.println("🔎 [getTimesheetsByCurrentUser] User ID: " + currentUser.getId());
        return timesheetRepository.findByUserId(currentUser.getId());
    }

    @Override
    public List<Timesheet> getTimesheetsByCurrentUserBetweenDates(LocalDate startDate, LocalDate endDate) {
        System.out.println("📅 [getTimesheetsByCurrentUserBetweenDates] Tarihler: " + startDate + " - " + endDate);
        User currentUser = SecurityUtil.getCurrentUser(userRepository);
        return timesheetRepository.findByUserIdAndDateBetween(currentUser.getId(), startDate, endDate);
    }

    @Override
    public Timesheet updateTimesheet(Long timesheetId, Timesheet updatedTimesheet) {
        System.out.println("✏️ [updateTimesheet] Güncelleme istenen ID: " + timesheetId);
        User currentUser = SecurityUtil.getCurrentUser(userRepository);

        Timesheet existing = timesheetRepository.findById(timesheetId)
                .orElseThrow(() -> new RuntimeException("❌ Timesheet bulunamadı."));

        if (!existing.getUser().getId().equals(currentUser.getId()) &&
                currentUser.getRole() != User.Role.ADMIN) {
            throw new RuntimeException("🚫 Yetkisiz güncelleme girişimi.");
        }

        existing.setDate(updatedTimesheet.getDate());
        existing.setStartTime(updatedTimesheet.getStartTime());
        existing.setEndTime(updatedTimesheet.getEndTime());
        existing.setDescription(updatedTimesheet.getDescription());

        System.out.println("✅ [updateTimesheet] Güncelleme başarılı.");
        return timesheetRepository.save(existing);
    }

    @Override
    public List<Timesheet> getAllTimesheets() {
        System.out.println("📥 [getAllTimesheets] Admin isteği kontrol ediliyor...");
        User currentUser = SecurityUtil.getCurrentUser(userRepository);

        if (currentUser.getRole() != User.Role.ADMIN) {
            throw new RuntimeException("🚫 Sadece admin tüm kayıtları görebilir.");
        }

        System.out.println("📦 [getAllTimesheets] Tüm kayıtlar çekiliyor...");
        return timesheetRepository.findAll();
    }
    @Override
    public List<Timesheet> getTimesheetsByUserId(Long userId) {
        System.out.println("📥 [getTimesheetsByUserId] Admin isteği kontrol ediliyor...");
        User currentUser = SecurityUtil.getCurrentUser(userRepository);

        if (currentUser.getRole() != User.Role.ADMIN) {
            throw new RuntimeException("🚫 Sadece admin kullanıcıların timesheet kayıtlarını görebilir.");
        }

        System.out.println("🔎 [getTimesheetsByUserId] Kullanıcı ID: " + userId);
        return timesheetRepository.findByUserId(userId);
    }

}
