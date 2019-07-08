package com.school.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModuleDto {

    private Long id;

    @NotNull
    @Length(min = 2, max = 20)
    private String name;

    @NotNull
    @Max(300)
    private Integer hours;

    @NotNull
    private List<SubjectDto> subjects;

    @NotNull
    @Max(10000)
    private Double price;
}
