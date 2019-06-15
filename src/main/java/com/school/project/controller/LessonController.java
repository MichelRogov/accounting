package com.school.project.controller;

import com.school.project.dto.LessonDto;
import com.school.project.model.entities.Lesson;
import com.school.project.service.LessonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping
public class LessonController {
    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/lessons")
    public ResponseEntity<LessonDto> createLesson(@RequestBody LessonDto lessonDto) {
        return ResponseEntity.ok()
                .body(convertLessonToLessonDto(lessonService
                        .createLesson(convertLessonDtoToLesson(lessonDto))));
    }

    @GetMapping("/lessons/{id}")
    public ResponseEntity<LessonDto>getLessonById(@PathVariable long id){
        return ResponseEntity.ok()
                .body(convertLessonToLessonDto(lessonService.getLesson(id)));
    }

    @GetMapping

    private Lesson convertLessonDtoToLesson(LessonDto lessonDto) { return modelMapper.map(lessonDto, Lesson.class); }
    private LessonDto convertLessonToLessonDto(Lesson lesson) { return modelMapper.map(lesson,LessonDto.class); }

}
