package com.school.project.service;

import com.school.project.model.User;

import java.util.List;

public interface UserService {

    void create(User newUser);

    User update(User user ,Long id);

    void delete(Long id);

    User findUserById(Long id);


}
