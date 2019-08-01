package com.school.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(allowGetters = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDto {

    private Long id;

    @NotNull
    private LessonDto lesson;

    @NotNull
    private UserDto user;

    @NotNull
    @JsonProperty("isPresent")
    private boolean isPresent;
}
