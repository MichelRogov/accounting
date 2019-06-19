package com.school.project.service;

import com.school.project.dto.LessonDto;
import com.school.project.model.entities.Lesson;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LessonService {
    Lesson createLesson(Lesson lesson);

    Lesson getLesson(Long id);

    List<Lesson> getAllLessonByGroup(Long id);

    List<Lesson> getAllLessonByTeacher(Long id);

    List<Lesson> getAllLessonBySubject(Long id);

    Lesson updateLesson(Lesson lesson,Long id);

    List<Lesson> getAllLessons();
}
