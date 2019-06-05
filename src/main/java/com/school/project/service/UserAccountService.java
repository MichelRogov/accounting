package com.school.project.service;

import com.school.project.model.types.UserAccountType;
import com.school.project.model.entities.User;
import com.school.project.model.entities.UserAccount;

public interface UserAccountService {
    UserAccount createUserAccount(User user, UserAccountType userAccountType);
}
