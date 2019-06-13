package com.school.project.service.impl;

import com.school.project.dto.UserDto;
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
    public User create(UserDto userDto) {
        User userForCreate = convertUserDtoToUser(userDto);
        userRepository.save(userForCreate);
        userAccountService.createUserAccount(userForCreate, UserAccountType.STUDENT);
        return userForCreate;
    }

    @Override
    public void update(UserDto user, Long id) {
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
    public UserDto getUserById(Long id) {
        Optional<User> byId = userRepository.findById(id);
        if (!byId.isPresent()) throw new UserNotFoundException("User with id :" + id + "is not found");
        return convertUserToUserDto(byId.get());
    }

    @Override
    public List<UserDto> getAllUsers(){
        return userRepository.findAll().stream()
                .map(s -> convertUserToUserDto(s))
                .collect(Collectors.toList());
    }
    private User convertUserDtoToUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    private UserDto convertUserToUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }
}
