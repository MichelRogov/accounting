package com.school.project.model.lesson;

import com.school.project.model.lesson.Group;
import com.school.project.model.module.Subject;
import com.school.project.model.user.User;

import java.util.Date;

public class Lesson {
    private Long id;
    private String thema;
    private Subject subject;
    private Date date;
    private Group group;
    private User teacher;
}
