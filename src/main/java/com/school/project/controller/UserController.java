package com.school.project.controller;

import com.school.project.dto.UserDto;
import com.school.project.repository.UserRepository;
import com.school.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/user/creation")
    public void createUser(@RequestBody UserDto userDto) {
        userService.create(userDto);
    }

}
