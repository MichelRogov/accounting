package com.school.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDto {

    private Long id;

    private LessonDto lesson;

    private UserDto user;

    @JsonProperty("isPresent")
    private boolean isPresent;
}
