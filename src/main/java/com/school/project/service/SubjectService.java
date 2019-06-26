package com.school.project.service;

import com.school.project.dto.SubjectDto;
import com.school.project.model.entities.Subject;

public interface SubjectService {

    Subject create( String subjectName );

    void update(SubjectDto user, Long id);

    void delete(Long id);

    Subject getSubjectById(Long id);
}
