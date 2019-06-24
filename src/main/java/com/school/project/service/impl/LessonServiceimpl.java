package com.school.project.service.impl;

import com.school.project.dto.LessonDto;
import com.school.project.exception.LessonNotFoundException;
import com.school.project.model.entities.Lesson;
import com.school.project.model.entities.User;
import com.school.project.repository.LessonRepository;
import com.school.project.repository.UserRepository;
import com.school.project.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
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
        if (!byId.isPresent()) throw new LessonNotFoundException("Lesson with id : " + id + " is not found");
        return byId.get();
    }

    @Override
    public List<Lesson> getAllLessonByGroup(Long id) {
        List<Lesson> lessonByGroupId = lessonRepository.getAllByGroupId(id);
        if (lessonByGroupId.isEmpty())
            throw new LessonNotFoundException("Lesson with Group id : " + id + " is not found");
        return lessonByGroupId;
    }

    @Override
    public List<Lesson> getAllLessonByTeacher(Long id) {
        List<Lesson> lessonByTeacherId = lessonRepository.getAllByTeacherId(id);
        if (lessonByTeacherId.isEmpty())
            throw new LessonNotFoundException("Lessonw with Teacher id : " + id + " is not found");
        return lessonByTeacherId;
    }

    @Override
    public List<Lesson> getAllLessonBySubject(Long id) {
        List<Lesson> lessonBySubjectId = lessonRepository.getAllBySubjectsId(id);
        System.out.println(lessonBySubjectId);
        if (lessonBySubjectId.isEmpty())
            throw new LessonNotFoundException("Lessons with Subject id : " + id + " is not found");
        return lessonBySubjectId;
    }

    @Override
    public void updateLesson(Lesson lessonToUpdate, Long id) {
        lessonToUpdate.setId(id);
        lessonRepository.saveAndFlush(lessonToUpdate);
    }

    @Override
    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }
}
