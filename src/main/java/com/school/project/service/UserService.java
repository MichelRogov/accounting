package com.school.project.service;

import com.school.project.model.entities.User;

import java.util.List;

public interface UserService {

    User create(User user);

    void update(User user, Long id);

    void delete(Long id);

    User getUserById(Long id);

    User getUserByIdParam(Long id);

    User getUserByLastName(String lastName);

    List<User> getAllUsers();

}
