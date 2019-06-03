package com.school.project.service;

import com.school.project.model.user.AccountType;
import com.school.project.model.user.User;
import com.school.project.model.user.UserAccount;

public interface UserAccountService {
    UserAccount createUserAccount(User user, AccountType accountType);
}
