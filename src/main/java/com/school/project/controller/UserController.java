package com.school.project.controller;

import com.school.project.dto.UserDto;
import com.school.project.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UserService userService;


    @PostMapping("/users")
    public void createUser(@RequestBody UserDto userDto) {

        userService.create(userDto);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUserByID(@PathVariable long id) {
        return ResponseEntity.ok()
                .body(userService.getUserById(id));
    }

    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto useDto, @PathVariable Long id) {
        userService.update(useDto, id);
        return ResponseEntity.ok()
                .body(userService.getUserById(id));
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }

}
