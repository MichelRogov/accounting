package com.school.project.model.lesson;

import com.school.project.model.module.Module;
import com.school.project.model.user.User;

import java.util.Date;
import java.util.List;

public class Group {
    private Long id;
    private Date startDate;
    private Module module;
    private List<User> users;
}
