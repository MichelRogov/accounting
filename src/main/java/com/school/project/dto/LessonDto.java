package com.school.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonDto {

    private Long id;

    private String thema;

    private List<SubjectDto> subjects;

    private Long creationDate;

    private GroupDto group;

    private UserDto teacher;
}
