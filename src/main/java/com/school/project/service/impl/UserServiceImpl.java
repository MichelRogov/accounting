package com.school.project.service.impl;

import com.school.project.exception.UserAccountNotFoundException;
import com.school.project.exception.UserNotFoundException;
import com.school.project.model.entities.User;
import com.school.project.model.entities.UserAccount;
import com.school.project.model.types.UserAccountType;
import com.school.project.repository.UserAccountRepository;
import com.school.project.repository.UserRepository;
import com.school.project.service.UserAccountService;
import com.school.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

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
    public void updateAccountRole(Long userId, Integer statusId){
        UserAccount account = getUserAccountByUserId(userId);
        account.setAccountRole(UserAccountType.getTypeById(statusId));
        userAccountRepository.saveAndFlush(account);
    }

    @Override
    public UserAccount getUserAccountByUserId(Long id) {
        Optional<UserAccount> account = userAccountRepository.getUserAccountByUserId(id);
        if (!account.isPresent()) throw new UserAccountNotFoundException("User account with userId : " + id + " is not found");
        return account.get();
    }

    @Override
    public void update(User user, Long id) {
        user.setId(getUserById(id).getId());
        userRepository.saveAndFlush(user);
    }

    @Override
    public void delete(Long id) {
        userAccountRepository.deleteUserAccountByUserId(id);
        userRepository.deleteById(id);
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> byId = userRepository.findById(id);
        if (!byId.isPresent()) throw new UserNotFoundException("User with id : " + id + " is not found");
        return byId.get();
    }

    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

}
