package com.school.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto {

    private Long id;

    @NotNull
    @DateTimeFormat(pattern = "yyyyMMdd")
    private Long startDate;

    @NotNull
    private ModuleDto module;

    @NotNull
    private List<UserDto> userList;

}
