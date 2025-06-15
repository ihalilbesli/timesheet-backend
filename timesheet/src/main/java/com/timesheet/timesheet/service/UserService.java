package com.timesheet.timesheet.service;

import com.timesheet.timesheet.model.User;

import java.util.Optional;

public interface UserService {

    User getCurrentUser();

    User save(User user);

    Optional<User> findById(Long id);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<User> findByUsername(String username);

    User updateCurrentUser(User updatedUser);

    void updatePassword(String oldPassword, String newPassword);

    void deleteUserById(Long id);


}
