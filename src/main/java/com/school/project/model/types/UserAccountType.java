package com.school.project.model.types;

public enum UserAccountType {
    TEACHER(1, "Teacher"),
    ADMIN(2, "Admin"),
    STUDENT(3, "Student");

    UserAccountType(Integer id, String name) {
    }

}
