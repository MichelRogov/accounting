package com.school.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModuleDto {

    private Long id;

    private String name;

    private Integer hours;

    private List<SubjectDto> subjects;

    private Double price;
}
