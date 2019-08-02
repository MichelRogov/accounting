package com.school.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDto {

    @ApiModelProperty(hidden = true)
    private Long id;

    private LessonDto lesson;

    private UserDto user;

    @JsonProperty("isPresent")
    private boolean isPresent;
}
