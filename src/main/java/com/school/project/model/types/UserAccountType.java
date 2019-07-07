package com.school.project.model.types;

public enum UserAccountType {

    TEACHER(1, "Teacher"),
    ADMIN(2, "Admin"),
    STUDENT(3, "Student");

    UserAccountType(Integer id, String name) {
    }

    public static UserAccountType getTypeById(Integer id) {
        if (id==1){
            return UserAccountType.TEACHER;
        }
        if (id==2){
            return UserAccountType.ADMIN;
        }
        return UserAccountType.STUDENT;
    }

}
