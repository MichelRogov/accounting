package com.school.project.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto {

    @ApiModelProperty(hidden = true)
    private Long id;

    private Long startDate;

    private ModuleDto module;

    private List<UserDto> userList;

}
