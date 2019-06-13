package com.school.project.service.impl;

import com.school.project.exception.SubjectNotFoundException;
import com.school.project.model.entities.Subject;
import com.school.project.repository.SubjectRepository;
import com.school.project.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    SubjectRepository subjectRepository;

    @Override
    public Subject createSubject(Subject subject) {
        subjectRepository.save(subject);
        return subject;
    }

    @Override
    public void updateSubject(Subject subject, Long id) {
        Subject sub = getSubjectById(id);
        sub.setName(subject.getName());
    }

    @Override
    public Subject getSubjectById(Long id) {
        Optional<Subject> byId = subjectRepository.findById(id);
        if (!byId.isPresent()) throw new SubjectNotFoundException("Subject with id :" + id + "is not found");
        return byId.get();
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll().stream().collect(Collectors.toList());
    }
}
