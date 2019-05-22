package com.school.project.service.impl;

import com.school.project.dto.UserDto;
import com.school.project.exception.UserNotFoundException;
import com.school.project.model.User;
import com.school.project.repository.UserRepository;
import com.school.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User create(UserDto newUser) {
        User user = User.builder()
                .firstName(newUser.getFirstName())
                .lastName(newUser.getLastName())
                .birthDate(newUser.getBirthDate())
                .email(newUser.getEmail())
                .group(newUser.getGroup())
                .build();
        userRepository.save(user);
        return user;
    }

    @Override
    public void update(UserDto user, Long id) {
        findUserById(id);
        User userToUpdate = create(user);
        userToUpdate.setId(id);
        userRepository.saveAndFlush(userToUpdate);
    }

    @Override
    public void delete(Long id) {
        findUserById(id);
        userRepository.deleteById(id);
    }

    @Override
    public UserDto findUserById(Long id) {
        Optional<User> byId = userRepository.findById(id);
        if (!byId.isPresent()) throw new UserNotFoundException("User with id :" + id + "is not found");
        return convertUserToUserDto(byId.get());
    }

    public UserDto convertUserToUserDto(User userToConvert) {
        return new UserDto(userToConvert.getFirstName()
                , userToConvert.getLastName()
                , userToConvert.getBirthDate(),
                userToConvert.getEmail(), userToConvert.getGroup());
    }


}
