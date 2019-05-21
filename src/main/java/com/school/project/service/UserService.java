package com.school.project.service;

import com.school.project.dto.UserDto;
import com.school.project.model.User;

import java.util.List;

public interface UserService {

    void create(UserDto newUser);

    User update(User user ,Long id);

    void delete(Long id);

    User findUserById(Long id);


}
