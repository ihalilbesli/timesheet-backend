package com.timesheet.timesheet.util;

import com.timesheet.timesheet.model.User;
import com.timesheet.timesheet.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SecurityUtil {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Kullanıcıyı veritabanından find ile getir (tam entity lazım olan yerlerde)
    public static User getCurrentUser(UserRepository userRepository) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null ||
                !authentication.isAuthenticated() ||
                "anonymousUser".equals(authentication.getName())) {
            throw new RuntimeException("❌ Giriş yapılmamış kullanıcı.");
        }

        Long userId;
        try {
            userId = Long.parseLong(authentication.getPrincipal().toString());
        } catch (Exception e) {
            throw new RuntimeException("❌ Kullanıcı kimliği geçersiz.");
        }

        System.out.println("🔍 [SecurityUtil] Aktif kullanıcı ID: " + userId);
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("❌ Kullanıcı bulunamadı: " + userId));
    }

    // Sadece ID döndür (proxy set edecek yerlerde)
    public static Long getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getName())) {
            throw new RuntimeException("❌ Giriş yapılmamış kullanıcı.");
        }
        Long userId = Long.parseLong(auth.getPrincipal().toString());
        System.out.println("🔑 [SecurityUtil] currentUserId(): " + userId);
        return userId;
    }
}
