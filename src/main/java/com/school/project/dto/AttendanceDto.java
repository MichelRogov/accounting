package com.school.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDto {

    private Long id;

    @NotNull
    private LessonDto lesson;

    @NotNull
    private UserDto user;

    @JsonProperty("isPresent")
    @NotNull
    private boolean isPresent;
}
