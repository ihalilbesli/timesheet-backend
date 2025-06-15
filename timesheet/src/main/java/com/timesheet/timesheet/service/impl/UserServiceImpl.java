package com.timesheet.timesheet.service.impl;

import com.timesheet.timesheet.model.User;
import com.timesheet.timesheet.repository.UserRepository;
import com.timesheet.timesheet.service.UserService;
import com.timesheet.timesheet.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;



    @Override
    public User getCurrentUser() {
        Long userId = SecurityUtil.getCurrentUserId();
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı."));
    }

    @Override
    public User save(User user) {

        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {

        return userRepository.findById(id);
    }

    @Override
    public boolean existsByUsername(String username) {

        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {

        return userRepository.existsByEmail(email);
    }

    @Override
    public Optional<User> findByUsername(String username) {

        return userRepository.findByUsername(username);
    }

    @Override
    public User updateCurrentUser(User updatedUser) {
        Long userId = SecurityUtil.getCurrentUserId();
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı."));

        // Başka bir kullanıcı bu username veya email’i kullanıyor mu kontrol et
        userRepository.findByUsername(updatedUser.getUsername()).ifPresent(user -> {
            if (!user.getId().equals(userId)) {
                throw new RuntimeException("Bu kullanıcı adı zaten kullanılıyor.");
            }
        });

        userRepository.findByEmail(updatedUser.getEmail()).ifPresent(user -> {
            if (!user.getId().equals(userId)) {
                throw new RuntimeException("Bu e-posta adresi zaten kullanılıyor.");
            }
        });

        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());

        return userRepository.save(existingUser);
    }


    @Override
    public void updatePassword(String oldPassword, String newPassword) {
        Long userId = SecurityUtil.getCurrentUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı."));

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new RuntimeException("Eski şifre hatalı.");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
    @Override
    public void deleteUserById(Long id) {
        User currentUser = SecurityUtil.getCurrentUser(userRepository);
        if (currentUser.getRole() != User.Role.ADMIN) {
            throw new RuntimeException("🚫 Bu işlemi sadece adminler yapabilir.");
        }

        // İstersen silmeden önce kullanıcı var mı kontrol et
        User userToDelete = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı: " + id));

        userRepository.deleteById(id);
    }
}
