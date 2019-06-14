package com.school.project.service.impl;

import com.school.project.dao.UserDao;
import com.school.project.model.entities.User;
import com.school.project.model.entities.UserAccount;
import com.school.project.model.types.UserAccountType;
import com.school.project.repository.UserAccountRepository;
import com.school.project.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private UserDao userDao;

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
}
