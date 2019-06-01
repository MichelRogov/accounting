package com.school.project.service.impl;

import com.school.project.model.AccountType;
import com.school.project.model.User;
import com.school.project.model.UserAccount;
import com.school.project.repository.UserAccountRepository;
import com.school.project.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountServiceImpl implements UserAccountService {
    @Autowired
    UserAccountRepository userAccountRepository;
    @Override
    public UserAccount createUserAccount(User user, AccountType accountType) {
        UserAccount userAccount=UserAccount.builder()
                .user((org.apache.catalina.User) user)
                .accountRole(accountType)
                .build();
        userAccountRepository.save(userAccount);
        return userAccount;
    }
}
