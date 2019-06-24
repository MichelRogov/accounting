package com.school.project.controller;

import com.school.project.dto.LessonDto;
import com.school.project.model.entities.Lesson;
import com.school.project.service.LessonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class LessonController {

    @Autowired
    LessonService lessonService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/lessons")
    public ResponseEntity<LessonDto> createLesson(@RequestBody LessonDto lessonDto) {
        return ResponseEntity.ok()
                .body(convertLessonToLessonDto(lessonService
                        .createLesson(convertLessonDtoToLesson(lessonDto))));
    }

    @GetMapping("/lessons")
    public List<LessonDto> getAllLessons() {
        return lessonService.getAllLessons().stream()
                .map(s -> convertLessonToLessonDto(s))
                .collect(Collectors.toList());
    }

    @GetMapping("/lessons/{id}")
    public ResponseEntity<LessonDto> getLessonById(@PathVariable long id) {
        return ResponseEntity.ok()
                .body(convertLessonToLessonDto(lessonService.getLesson(id)));
    }

    @GetMapping("/lessons/group/{id}")
    public ResponseEntity<List<LessonDto>> getAllLessonsByGroup(@PathVariable long id) {
        return ResponseEntity.ok().body(lessonService.getAllLessonByGroup(id)
                .stream().map(s -> convertLessonToLessonDto(s)).collect(Collectors.toList()));

    }

    @GetMapping("/lessons/teacher/{id}")
    public ResponseEntity<List<LessonDto>> getAllLessonsByTeacher(@PathVariable long id) {
        return ResponseEntity.ok().body(lessonService.getAllLessonByTeacher(id)
                .stream().map(s -> convertLessonToLessonDto(s)).collect(Collectors.toList()));
    }

    @GetMapping("/lessons/subject/{id}")
    public ResponseEntity<List<LessonDto>> getAllLessonsBySubject(@PathVariable long id) {
        System.out.println(lessonService.getAllLessonBySubject(id) + "subject ");
        return ResponseEntity.ok().body(lessonService.getAllLessonBySubject(id)
                .stream().map(s -> convertLessonToLessonDto(s)).collect(Collectors.toList()));
    }

    @PutMapping("/lessons/{id}")
    public void updateLesson(@RequestBody LessonDto lessonDto, @PathVariable Long id) {
        lessonService.updateLesson(convertLessonDtoToLesson(lessonDto), id);
    }

    private Lesson convertLessonDtoToLesson(LessonDto lessonDto) {
        return modelMapper.map(lessonDto, Lesson.class);
    }

    private LessonDto convertLessonToLessonDto(Lesson lesson) {
        return modelMapper.map(lesson, LessonDto.class);
    }

}
