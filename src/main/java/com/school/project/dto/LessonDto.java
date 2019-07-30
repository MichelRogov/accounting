package com.school.project.dto;

import com.school.project.configuration.AppConfiguration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

import static com.school.project.configuration.AppConfiguration.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonDto {

    @Null(groups = {CreateValidation.class})
    @NotNull(groups = {UpdateValidtion.class})
    private Long id;

    @NotEmpty(groups = {CreateValidation.class})
    @Length(min = 2, max = 20)
    private String thema;

    @NotEmpty(groups = {CreateValidation.class})
    private List<SubjectDto> subjects;

    @NotNull(groups = {UpdateValidtion.class})
    @Null(groups = {CreateValidation.class})
    @DateTimeFormat(pattern = "yyyyMMdd")
    private Long createdDate;

    @NotNull(groups = {CreateValidation.class})
    private GroupDto group;

    @NotNull(groups = {CreateValidation.class})
    private UserDto teacher;
}
