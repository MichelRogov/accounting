package com.school.project.controller;

import com.school.project.dto.SubjectDto;
import com.school.project.model.entities.Subject;
import com.school.project.service.SubjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class SubjectController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    SubjectService subjectService;

    @PostMapping("/subject")
    public void createSubject(@RequestBody SubjectDto subjectDto) {
        subjectService.create(convertSubjectDtoToSubject(subjectDto));}

    @GetMapping("/subject/{id}")
    public ResponseEntity<SubjectDto> getSubjectById(@PathVariable long id) {
        return ResponseEntity.ok()
                .body(convertSubjectToSubjectDto(subjectService.getSubjectById(id)));
    }

    @GetMapping("/subjects")
    public List<SubjectDto> getAllSubjects() {
        return subjectService.getAllSubjects()
                .stream()
                .map(s -> convertSubjectToSubjectDto(s))
                .collect(Collectors.toList());
    }

    @PutMapping("/subject/{id}")
    public ResponseEntity updateSubject(@RequestBody SubjectDto subjectDto, @PathVariable Long id) {
        subjectService.update(convertSubjectDtoToSubject(subjectDto), id);
        return ResponseEntity.ok()
                .build();
    }

    private Subject convertSubjectDtoToSubject(SubjectDto subjectDto) {
        return modelMapper.map(subjectDto, Subject.class);
    }

    private SubjectDto convertSubjectToSubjectDto(Subject subject) {
        return modelMapper.map(subject, SubjectDto.class);
    }
}


