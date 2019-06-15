package com.school.project.service.impl;

import com.school.project.dto.LessonDto;
import com.school.project.exception.LessonNotFoundException;
import com.school.project.model.entities.Lesson;
import com.school.project.repository.LessonRepository;
import com.school.project.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class LessonServiceimpl implements LessonService {

    private final LessonRepository lessonRepository;
    @Autowired
    public LessonServiceimpl(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public Lesson createLesson(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    @Override
    public Lesson getLesson(Long id) {
        Optional<Lesson> byId = lessonRepository.findById(id);
        if (!byId.isPresent()) throw new LessonNotFoundException("Lesson with id : " + id + "is not found");
        return byId.get();
    }

    @Override
    public Lesson getLessonByGroup(Long id) {
        Optional<Lesson> lessonByGroupId = lessonRepository.getLessonByGroupId(id);
        if (!lessonByGroupId.isPresent()) throw new LessonNotFoundException("Lesson with id : " + id + "is not found");
        return lessonByGroupId.get();
    }

    @Override
    public Lesson getLessonByTeacher(Long id) {
        Optional<Lesson> lessonByTeacherId = lessonRepository.getLessonByTeacherId(id);
        if (!lessonByTeacherId.isPresent())
            throw new LessonNotFoundException("Lesson with id : " + id + "is not found");
        return lessonByTeacherId.get();
    }

    @Override
    public Lesson getLessonBySubject(Long id) {
        Optional<Lesson> lessonBySubjectId = lessonRepository.getLessonBySubjectId(id);
        if (!lessonBySubjectId.isPresent())
            throw new LessonNotFoundException("Lesson with id : " + id + "is not found");
        return lessonBySubjectId.get();
    }

    @Override
    public Lesson updateLesson(LessonDto lessonDto) {
        Lesson lesson = getLesson(lessonDto.getId());
        lesson.setThema(lessonDto.getThema());
        lesson.setSubject(lessonDto.getSubject());
        lesson.setGroup(lessonDto.getGroup());
        lesson.setTeacher(lessonDto.getTeacher());
        return lessonRepository.saveAndFlush(lesson);
    }
}
