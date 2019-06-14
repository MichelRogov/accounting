package com.school.project.controller;

import com.school.project.dto.UserDto;
import com.school.project.model.entities.User;
import com.school.project.repository.UserRepository;
import com.school.project.service.SecurityService;
import com.school.project.service.UserService;
import com.school.project.validator.UserValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model){
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration (@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model){
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()){
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());

        return "redirect:/ welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String Login(Model model, String error, String logout){
        if (error!= null){
            model.addAttribute("error", "Username or password is incorrect");
        }

        if (logout!=null){
            model.addAttribute("message", "Logged out successfully.");
        }

        return "login";
    }

    @RequestMapping(value = {"/","/welcome"}, method = RequestMethod.GET)
    public  String welcome(Model model){
        return "welcome";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Model model){
        return "admin";
    }

    @PostMapping("/users")
    public void createUser(@RequestBody UserDto userDto) {

        userService.create(convertUserDtoToUser(userDto));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUserByID(@PathVariable long id) {
        return ResponseEntity.ok()
                .body(convertUserToUserDto(userService.getUserById(id)));
    }

    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(s -> convertUserToUserDto(s))
                .collect(Collectors.toList());
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto useDto, @PathVariable Long id) {
        userService.update(useDto, id);
        return ResponseEntity.ok()
                .body(convertUserToUserDto(userService.getUserById(id)));
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
