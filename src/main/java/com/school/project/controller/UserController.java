package com.school.project.controller;

import com.school.project.dto.UserDto;
import com.school.project.repository.UserRepository;
import com.school.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/user/creation")
    public void createUser(@RequestBody UserDto userDto) {
        userService.create(userDto);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getUserByID(@PathVariable long id) {
        return ResponseEntity.ok()
                .body(userService.convertUserToUserDto(userService.findUserById(id)));
    }

    @GetMapping("/user")
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(s -> userService.convertUserToUserDto(s))
                .collect(Collectors.toList());
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto useToUpgrade, @PathVariable Long id) {
        userService.update(useToUpgrade, id);
        return ResponseEntity.ok()
                .body(userService.convertUserToUserDto(userService.findUserById(id)));
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }

}
