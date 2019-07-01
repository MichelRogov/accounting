package com.school.project.controller;


import com.school.project.dto.SubjectDto;
import com.school.project.model.entities.Subject;
import com.school.project.repository.SubjectRepository;
import com.school.project.service.SubjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
    @Autowired
    SubjectRepository subjectRepository;

    @PostMapping("/subject")
    public void createSubject(@RequestBody SubjectDto subjectDto) {

        subjectService.create(subjectDto.getName());
    }

    @GetMapping("/subjects/{id}")
    public ResponseEntity<SubjectDto> getSubjectByID(@PathVariable long id) {
        return ResponseEntity.ok()
                .body(convertSubjectToSubjectDto(subjectService.getSubjectById(id)));
    }

    @GetMapping("/subjects")
    public List<SubjectDto> getAllSubjects() {
        return subjectRepository.findAll().stream()
                .map(s -> convertSubjectToSubjectDto(s))
                .collect(Collectors.toList());
    }

    @PutMapping("/subjects/{id}")
    public ResponseEntity<SubjectDto> updateSubject(@RequestBody SubjectDto subjectDto, @PathVariable Long id) {
        subjectService.update(subjectDto, id);
        return ResponseEntity.ok()
                .body(convertSubjectToSubjectDto(subjectService.getSubjectById(id)));
    }

    @DeleteMapping("/subjects/{id}")
    public void deleteSubject(@PathVariable Long id) {
        subjectService.delete(id);
    }


    private SubjectDto convertSubjectToSubjectDto(Subject sub) {
        return modelMapper.map(sub, SubjectDto.class);
    }

}