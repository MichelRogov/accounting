package com.school.project.controller;

import com.school.project.model.entities.Subject;
import com.school.project.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @PostMapping("/subjects")
    public void createSubject(@RequestBody Subject subject) {
        subjectService.createSubject(subject);
    }

    @GetMapping("/subjects/{id}")
    public ResponseEntity<Subject> getSubjectByID(@PathVariable long id) {
        return ResponseEntity.ok()
                .body(subjectService.getSubjectById(id));
    }

    @GetMapping("/subjects")
    public List<Subject> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @PutMapping("/subjects/{id}")
    public ResponseEntity<Subject> updateSubject(@RequestBody Subject subject, @PathVariable Long id) {
        subjectService.updateSubject(subject, id);
        return ResponseEntity.ok()
                .body(subjectService.getSubjectById(id));
    }
}
