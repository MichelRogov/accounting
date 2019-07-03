package com.school.project.controller;

import com.school.project.dto.UserDto;
import com.school.project.model.entities.User;
import com.school.project.model.types.UserAccountType;
import com.school.project.service.UserAccountService;
import com.school.project.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserService userService;

    @Autowired
    UserAccountService userAccountService;

    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok()
                .body(convertUserToUserDto(userService
                        .create(convertUserDtoToUser(userDto))));
    }

    @PutMapping("/users/userAccounts/{id}")
    public ResponseEntity updateUserAccountType(@PathVariable long id, @RequestBody int accountType){
        userAccountService.getUserAccountByUserId(id).setAccountRole(chooseType(accountType));
        return ResponseEntity.ok().build();
    }

    private UserAccountType chooseType(int accountType){
        if(accountType==1){
            return UserAccountType.TEACHER;
        }
        if(accountType==2){
            return UserAccountType.ADMIN;
        }
        return UserAccountType.STUDENT;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUserByID(@PathVariable long id) {
        return ResponseEntity.ok()
                .body(convertUserToUserDto(userService.getUserById(id)));
    }

    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers()
                .stream()
                .map(s -> convertUserToUserDto(s))
                .collect(Collectors.toList());
    }

    @PutMapping("/users/{id}")
    public ResponseEntity updateUser(@RequestBody UserDto userDto, @PathVariable Long id) {
        userService.update(convertUserDtoToUser(userDto), id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }

    private User convertUserDtoToUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    private UserDto convertUserToUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

}
