package com.school.project.service.impl;

import com.school.project.dto.SubjectDto;
import com.school.project.exception.SubjectNotFoundException;
import com.school.project.model.entities.Subject;
import com.school.project.repository.SubjectRepository;
import com.school.project.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    SubjectRepository subjectRepository;

    @Override
    public Subject create( String subjectName ) {

        Subject subject = Subject.builder().name(subjectName).build();
        subjectRepository.save(subject);

        return subject;
    }

    @Override
    public void update(SubjectDto subDto, Long id) {
        Subject sub = getSubjectById(id);
        sub.setName(subDto.getName());
        subjectRepository.saveAndFlush(sub);
    }

    @Override
    public void delete(Long id) {
        subjectRepository.delete(getSubjectById(id));
    }

    @Override
    public Subject getSubjectById(Long id) {
        Optional<Subject> subjectOpt = subjectRepository.findById(id);
        if (!subjectOpt.isPresent()) throw new SubjectNotFoundException("User with id :" + id + "is not found");
        return subjectOpt.get();
    }
}