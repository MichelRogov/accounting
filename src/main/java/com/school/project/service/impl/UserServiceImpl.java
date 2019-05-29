package com.school.project.service.impl;

import com.school.project.dto.UserDto;
import com.school.project.exception.UserNotFoundException;
import com.school.project.model.AccountType;
import com.school.project.model.User;
import com.school.project.model.UserAccount;
import com.school.project.repository.UserAccountRepository;
import com.school.project.repository.UserRepository;
import com.school.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserAccountRepository userAccountRepository;

    @Override
    public User create(UserDto newUser) {
        User user = User.builder()
                .firstName(newUser.getFirstName())
                .lastName(newUser.getLastName())
                .birthDate(newUser.getBirthDate())
                .email(newUser.getEmail())
                .phoneNumber(newUser.getPhoneNumber())
                .build();
        userRepository.save(user);
        createUserAccount(user,"STUDENT");
        return user;
    }
    public void createUserAccount(User user,String accountType){
        UserAccount userAccount=UserAccount.builder()
        .userId(user)
                .accountRole(AccountType.valueOf(accountType))
                .build();
        userAccountRepository.save(userAccount);

    }

    @Override
    public void update(UserDto user, Long id) {
        User userById = findUserById(id);
        userById.setFirstName(user.getFirstName());
        userById.setLastName(user.getLastName());
        userById.setBirthDate(user.getBirthDate());
        userById.setEmail(user.getEmail());
        userById.setPhoneNumber(user.getPhoneNumber());
        userRepository.saveAndFlush(userById);
        ;
    }

    @Override
    public void delete(Long id) {
        findUserById(id);
        userRepository.deleteById(id);
    }

    @Override
    public User findUserById(Long id) {
        Optional<User> byId = userRepository.findById(id);
        if (!byId.isPresent()) throw new UserNotFoundException("User with id :" + id + "is not found");
        return byId.get();
    }

    public UserDto convertUserToUserDto(User userToConvert) {
        return new UserDto(userToConvert.getFirstName()
                , userToConvert.getLastName()
                , userToConvert.getBirthDate()
                , userToConvert.getEmail()
                , userToConvert.getPhoneNumber());
                /*, userToConvert.ge*/
    }


}
