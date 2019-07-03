package com.school.project.service;

import com.school.project.model.entities.User;
import com.school.project.model.entities.UserAccount;
import com.school.project.model.types.UserAccountType;

public interface UserAccountService {

    UserAccount createUserAccount(User user, UserAccountType userAccountType);

    UserAccount getUserAccountById(Long id);

    UserAccount getUserAccountByUserId(Long id);

}
