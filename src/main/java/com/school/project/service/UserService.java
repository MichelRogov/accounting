package com.school.project.service;

import com.school.project.dto.UserDto;
import com.school.project.model.User;

import java.util.List;

public interface UserService {

    User create(UserDto newUser);

    void update(UserDto user, Long id);

    void delete(Long id);

    User findUserById(Long id);

    UserDto convertUserToUserDto(User userToConvert);
}
