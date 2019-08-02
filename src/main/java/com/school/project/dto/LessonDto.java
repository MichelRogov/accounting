package com.school.project.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonDto {

    @ApiModelProperty(hidden = true)
    private Long id;

    private String thema;

    private List<SubjectDto> subjects;

    private Long createdDate;

    private GroupDto group;

    private UserDto teacher;
}
