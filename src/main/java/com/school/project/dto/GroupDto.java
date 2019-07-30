package com.school.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.school.project.configuration.AppConfiguration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

import static com.school.project.configuration.AppConfiguration.*;

@JsonIgnoreProperties(allowGetters = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto {

    @Null(groups = {CreateValidation.class})
    @NotNull(groups = {UpdateValidtion.class})
    private Long id;

    @NotNull(groups = {CreateValidation.class})
    @DateTimeFormat(pattern = "yyyyMMdd")
    private Long startDate;

    @NotNull(groups = {CreateValidation.class})
    private ModuleDto module;

    @NotNull(groups = {CreateValidation.class})
    private List<UserDto> userList;

}
