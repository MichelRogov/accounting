package com.school.project.service;

import com.school.project.dto.UserDto;
import com.school.project.model.entities.User;

public interface UserService {

    User create(User User);

    void update(UserDto user, Long id);

    void delete(Long id);

    User getUserById(Long id);

}
