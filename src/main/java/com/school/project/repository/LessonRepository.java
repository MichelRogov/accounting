package com.school.project.repository;

import com.school.project.model.entities.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface LessonRepository extends JpaRepository<Lesson,Long> {

    @Transactional
    Optional<Lesson> getLessonByGroupId(Long id);

    @Transactional
    Optional<Lesson> getLessonByTeacherId(Long id);

    @Transactional
    Optional<Lesson> getLessonBySubjectId(Long id);

}
