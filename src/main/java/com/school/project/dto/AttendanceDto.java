package com.school.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import static com.school.project.configuration.AppConfiguration.CreateValidation;
import static com.school.project.configuration.AppConfiguration.UpdateValidtion;

@JsonIgnoreProperties(allowGetters = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDto {

    @Null(groups = {CreateValidation.class})
    @NotNull(groups = {UpdateValidtion.class})
    private Long id;

    @NotNull(groups = {CreateValidation.class})
    private LessonDto lesson;

    @NotNull(groups = {CreateValidation.class})
    private UserDto user;

    @NotNull(groups = {CreateValidation.class})
    @JsonProperty("isPresent")
    private boolean isPresent;
}
