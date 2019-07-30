package com.school.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

import static com.school.project.configuration.AppConfiguration.CreateValidation;
import static com.school.project.configuration.AppConfiguration.UpdateValidtion;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModuleDto {

    @Null(groups = {CreateValidation.class})
    @NotNull(groups = {UpdateValidtion.class})
    private Long id;

    @NotEmpty(groups = {CreateValidation.class})
    @Length(min = 2, max = 20)
    private String name;

    @NotNull(groups = {CreateValidation.class})
    @Max(300)
    private Integer hours;

    @NotEmpty(groups = {CreateValidation.class})
    private List<SubjectDto> subjects;

    @NotNull(groups = {CreateValidation.class})
    private Double price;
}
