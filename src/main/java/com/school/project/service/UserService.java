package com.school.project.service;

import com.school.project.dto.UserDto;
import com.school.project.model.User;

public interface UserService {

    User create(User user);

    void update(UserDto useDto, Long id);

    void delete(Long id);

    User getUserById(Long id);
}
