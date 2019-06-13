package com.school.project.service;

import com.school.project.dto.UserDto;
import com.school.project.model.entities.User;

import java.util.List;

public interface UserService {

    User create(UserDto userDto);

    void update(UserDto user, Long id);

    void delete(Long id);

    UserDto getUserById(Long id);

    List<UserDto> getAllUsers();

}
