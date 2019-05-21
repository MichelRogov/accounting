package com.school.project.service.impl;

import com.school.project.dto.UserDto;
import com.school.project.exception.UserNotFoundException;
import com.school.project.model.User;
import com.school.project.repository.UserRepository;
import com.school.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public void create(UserDto newUser) {
        User user = User.builder()
                .firstName(newUser.getFirstName())
                .lastName(newUser.getLastName())
                .birthDate(newUser.getBirthDate())
                .group(newUser.getGroup())
                .build();
        userRepository.save(user);
    }

    @Override
    public User update(User user,Long id) {
        findUserById(id);
        user.setId(id);
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        findUserById(id);
        userRepository.deleteById(id);
    }

    @Override
    public User findUserById(Long id) {
        Optional<User> byId =userRepository.findById(id);
        if (!byId.isPresent())throw new UserNotFoundException("User with id :"+id+"is not found");
        return byId.get();
    }

}
