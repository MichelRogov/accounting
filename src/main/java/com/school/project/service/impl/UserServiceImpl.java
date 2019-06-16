package com.school.project.service.impl;

import com.school.project.exception.UserNotFoundException;
import com.school.project.model.entities.User;
import com.school.project.model.types.UserAccountType;
import com.school.project.repository.UserAccountRepository;
import com.school.project.repository.UserRepository;
import com.school.project.service.UserAccountService;
import com.school.project.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserAccountService userAccountService;
    @Autowired
    UserAccountRepository userAccountRepository;


    @Override
    public User create(User user) {
        userRepository.save(user);
        userAccountService.createUserAccount(user, UserAccountType.STUDENT);
        return user;
    }

    @Override
    public void update(User user, Long id) {
        User userById;
        Optional<User> byId = userRepository.findById(id);
        if (!byId.isPresent()) throw new UserNotFoundException("User with id :" + id + "is not found");
        else userById = byId.get();
        userById.setFirstName(user.getFirstName());
        userById.setLastName(user.getLastName());
        userById.setBirthDate(user.getBirthDate());
        userById.setEmail(user.getEmail());
        userById.setPhoneNumber(user.getPhoneNumber());
        userRepository.saveAndFlush(userById);
    }

    @Override
    public void delete(Long id) {
        userAccountRepository.deleteUserAccountByUserId(id);
        userRepository.deleteById(id);
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> byId = userRepository.findById(id);
        if (!byId.isPresent()) throw new UserNotFoundException("User with id :" + id + "is not found");
        return byId.get();
    }

    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll().stream()
                .collect(Collectors.toList());
    }

}
