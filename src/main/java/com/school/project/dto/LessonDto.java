package com.school.project.dto;

import com.school.project.configuration.AppConfiguration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

import static com.school.project.configuration.AppConfiguration.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonDto {

    private Long id;

    @NotEmpty
    @Length(min = 2, max = 20)
    private String thema;

    @NotEmpty
    private List<SubjectDto> subjects;

    @NotNull
    @DateTimeFormat(pattern = "yyyyMMdd")
    private Long createdDate;

    @NotNull
    private GroupDto group;

    @NotNull
    private UserDto teacher;
}
