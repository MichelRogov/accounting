package com.school.project.model;

import javax.persistence.Table;

@Table(name = "ACCOUNT_TYPE")
public enum AccountType {
    TEACHER(1, "Teacher"),
    ADMIN(2, "Admin"),
    STUDENT(3, "Student");

    AccountType(Integer id, String name) {
    }

}
