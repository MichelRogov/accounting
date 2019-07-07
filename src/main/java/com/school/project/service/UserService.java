package com.school.project.service;

import com.school.project.model.entities.User;
import com.school.project.model.entities.UserAccount;

import java.util.List;

public interface UserService {

    User create(User user);

    void update(User user, Long id);

    void delete(Long id);

    User getUserById(Long id);

    List<User> getAllUsers();

    void updateAccountRole(Long userId, Integer statusId);

    UserAccount getUserAccountByUserId(Long id);

}
