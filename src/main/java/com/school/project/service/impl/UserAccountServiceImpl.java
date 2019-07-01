package com.school.project.service.impl;

import com.school.project.exception.UserAccountNotFoundException;
import com.school.project.model.entities.User;
import com.school.project.model.entities.UserAccount;
import com.school.project.model.types.UserAccountType;
import com.school.project.repository.UserAccountRepository;
import com.school.project.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    UserAccountRepository userAccountRepository;

    @Override
    public UserAccount createUserAccount(User user, UserAccountType userAccountType) {
        UserAccount userAccount = UserAccount.builder()
                .user(user)
                .accountRole(userAccountType)
                .build();
        userAccountRepository.save(userAccount);
        return userAccount;
    }

    @Override
    public UserAccount getUserAccountById(Long id) {
        Optional<UserAccount> byId = userAccountRepository.findById(id);
        if (!byId.isPresent()) throw new UserAccountNotFoundException("User account with id : " + id + " is not found");
        return byId.get();
    }

}
