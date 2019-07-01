package com.school.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDto {

    Long id;

    private LessonDto lesson;

    private UserDto user;

    private boolean isPresent;
}
