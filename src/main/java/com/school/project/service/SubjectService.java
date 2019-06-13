package com.school.project.service;

import com.school.project.model.entities.Subject;

import java.util.List;

public interface SubjectService {

    Subject createSubject(Subject subject);

    void updateSubject(Subject subject, Long id);

    Subject getSubjectById(Long id);

    List<Subject> getAllSubjects();

}
