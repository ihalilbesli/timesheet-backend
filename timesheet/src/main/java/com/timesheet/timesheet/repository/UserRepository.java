package com.timesheet.timesheet.repository;

import com.timesheet.timesheet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<User> findByUsername(String username);

    List<User> findByCreatedAtBetween(LocalDate startDate, LocalDate endDate);

    Optional<User> findByEmail(String email); // 🔹 Bunu ekle




}