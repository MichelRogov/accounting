package com.school.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import static com.school.project.configuration.AppConfiguration.CreateValidation;
import static com.school.project.configuration.AppConfiguration.UpdateValidtion;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDto {

    @Null(groups = {CreateValidation.class})
    @NotNull(groups = {UpdateValidtion.class})
    private Long id;

    @NotEmpty(groups = {CreateValidation.class})
    @Length(min = 2, max = 20)
    private String name;
}
