package com.school.project.service.impl;

import com.school.project.dto.LessonDto;
import com.school.project.exception.LessonNotFoundException;
import com.school.project.model.entities.Lesson;
import com.school.project.repository.LessonRepository;
import com.school.project.service.LessonService;

import java.util.Optional;

public class LessonServiceimpl implements LessonService {

    private final LessonRepository lessonRepository;

    public LessonServiceimpl(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public Lesson createLesson(Lesson lesson) {
        lessonRepository.save(lesson);
        return lesson;
    }

    @Override
    public Lesson getLesson(Long id) {
        Optional<Lesson> byId = lessonRepository.findById(id);
        if (!byId.isPresent()) throw new LessonNotFoundException("Lesson with id : " + id + "is not found");
        return byId.get();
    }

    @Override
    public Lesson getLessonByGroup(Long id) {
        return null;
    }

    @Override
    public Lesson getLessonByTeacher(Long id) {
        return null;
    }

    @Override
    public Lesson getLessonBySubject(Long id) {
        return null;
    }

    @Override
    public Lesson updateLesson(LessonDto lessonDto) {
        return null;
    }
}
