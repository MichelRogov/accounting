package com.school.project.service;

import com.school.project.model.AccountType;
import com.school.project.model.User;
import com.school.project.model.UserAccount;

public interface UserAccountService {
    UserAccount createUserAccount(User user, AccountType accountType);
}
