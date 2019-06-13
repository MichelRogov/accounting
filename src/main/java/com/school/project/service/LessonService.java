package com.school.project.service;

import com.school.project.dto.LessonDto;
import com.school.project.model.entities.Lesson;

public interface LessonService {
    Lesson createLesson(Lesson lesson);
    Lesson getLesson (Long id);
    Lesson getLessonByGroup (Long id);
    Lesson getLessonByTeacher(Long id);
    Lesson getLessonBySubject(Long id);
    Lesson updateLesson(LessonDto lessonDto);
}
