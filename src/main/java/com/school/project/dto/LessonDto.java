package com.school.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonDto {

    private Long id;

    @NotNull
    @Length(min = 2, max = 20)
    private String thema;

    @NotNull
    private List<SubjectDto> subjects;

    @NotNull
    @DateTimeFormat(pattern = "yyyyMMdd")
    private Long createdDate;

    @NotNull
    private GroupDto group;

    @NotNull
    private UserDto teacher;
}
